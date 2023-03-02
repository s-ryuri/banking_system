package numble.banking.account.application;

import lombok.extern.slf4j.Slf4j;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.application.dto.AccountTransferRequest;
import numble.banking.account.persistence.Account;
import numble.banking.account.persistence.RedisLockRepository;
import numble.banking.friend.application.FriendService;
import numble.banking.friend.application.dto.FriendAddRequest;
import numble.banking.member.application.MemberService;
import numble.banking.member.application.dto.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RedisLockRepository redisLockRepository;


    private void setUp() {
        final SignUpRequest signUpRequest1 = new SignUpRequest("member1", "12345");
        final SignUpRequest signUpRequest2 = new SignUpRequest("member2", "12345");
        final SignUpRequest signUpRequest3 = new SignUpRequest("member3", "12345");

        memberService.signUp(signUpRequest1);
        memberService.signUp(signUpRequest2);
        memberService.signUp(signUpRequest3);

        final FriendAddRequest friendAddRequest1 = new FriendAddRequest(2L, 1L);
        final FriendAddRequest friendAddRequest2 = new FriendAddRequest(3L, 1L);
        final FriendAddRequest friendAddRequest3 = new FriendAddRequest(1L, 2L);
        final FriendAddRequest friendAddRequest4 = new FriendAddRequest(1L, 3L);

        friendService.addFriend(friendAddRequest1);
        friendService.addFriend(friendAddRequest2);
        friendService.addFriend(friendAddRequest3);
        friendService.addFriend(friendAddRequest4);

        final Account account1 = new Account(1L, 6000L);
        final Account account2 = new Account(2L, 3000L);
        final Account account3 = new Account(3L, 4000L);

        accountService.save(account1);
        accountService.save(account2);
        accountService.save(account3);
    }


    private void afterDelete() {
        accountService.deleteAll();
        friendService.deleteAll();
        memberService.deleteAll();
        redisLockRepository.unLock("accountLockKey");
    }


    @Test
    void 단건_계좌이체_제대로_되는지_확인() {
        setUp();
        final AccountTransferRequest accountTransferRequest = AccountTransferRequest.builder()
                                                                                    .fromAccountId(1L)
                                                                                    .fromMemberId(1L)
                                                                                    .toAccountId(2L)
                                                                                    .toMemberId(2L)
                                                                                    .balance(100L)
                                                                                    .build();

        accountService.transfer(accountTransferRequest);

        final AccountGetResponse accountGetResponse1 = accountService.getAccount(1L).get(0);
        final AccountGetResponse accountGetResponse2 = accountService.getAccount(2L).get(0);

        afterDelete();
        assertThat(accountGetResponse1.getBalance()).isEqualTo(5900L);
        assertThat(accountGetResponse2.getBalance()).isEqualTo(3100L);
    }

    @Test
    void 동시에_계좌이체를_요청했을때_한명이_똑같은_계좌에_10번_실행() throws InterruptedException {
        setUp();
        int threadCount = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(30);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        final AccountTransferRequest accountTransferRequest = AccountTransferRequest.builder()
                                                                                    .fromAccountId(1L)
                                                                                    .fromMemberId(1L)
                                                                                    .toAccountId(2L)
                                                                                    .toMemberId(2L)
                                                                                    .balance(100L)
                                                                                    .build();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    accountService.transfer(accountTransferRequest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        final Account account1 = accountRepository.findAllByMemberId(1L).get(0);
        final Account account2 = accountRepository.findAllByMemberId(2L).get(0);
        afterDelete();
        assertThat(account1.getBalance()).isEqualTo(5000L);
        assertThat(account2.getBalance()).isEqualTo(4000L);

    }


    @Test
    void 여러명이_한계좌에_이체() throws InterruptedException {
        setUp();
        int threadCount = 2;
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        final AccountTransferRequest accountTransferRequest1 = AccountTransferRequest.builder()
                                                                                     .fromAccountId(3L)
                                                                                     .fromMemberId(3L)
                                                                                     .toAccountId(1L)
                                                                                     .toMemberId(1L)
                                                                                     .balance(100L)
                                                                                     .build();

        final AccountTransferRequest accountTransferRequest2 = AccountTransferRequest.builder()
                                                                                     .fromAccountId(2L)
                                                                                     .fromMemberId(2L)
                                                                                     .toAccountId(1L)
                                                                                     .toMemberId(1L)
                                                                                     .balance(100L)
                                                                                     .build();
        executorService.submit(() -> {
            accountService.transfer(accountTransferRequest2);
            countDownLatch.countDown();
        });

        executorService.submit(() -> {
            accountService.transfer(accountTransferRequest1);
            countDownLatch.countDown();
        });

        countDownLatch.await();

        final Account account1 = accountRepository.findAllByMemberId(1L).get(0);
        final Account account2 = accountRepository.findAllByMemberId(2L).get(0);
        final Account account3 = accountRepository.findAllByMemberId(3L).get(0);

        afterDelete();
        assertThat(account1.getBalance()).isEqualTo(6200L);
        assertThat(account2.getBalance()).isEqualTo(2900L);
        assertThat(account3.getBalance()).isEqualTo(3900L);
    }
}