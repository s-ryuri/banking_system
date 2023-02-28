package numble.banking.account.application;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.application.dto.AccountTransferRequest;
import numble.banking.account.application.dto.AccountTransferResponse;
import numble.banking.account.application.exception.NotFriendException;
import numble.banking.account.persistence.Account;
import numble.banking.friend.application.FriendService;
import numble.banking.member.application.MemberService;
import numble.banking.member.persistence.exception.NotFoundMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final MemberService memberService;

    private final FriendService friendService;

    @Transactional(readOnly = true)
    public List<AccountGetResponse> getAccount(Long memberId) {
        checkMemberId(memberId);

        final List<Account> accounts = accountRepository.findAllByMemberId(memberId);

        return getAccountGetResponses(accounts);
    }

    private void checkMemberId(Long memberId) {
        if (!memberService.existsById(memberId)) {
            throw new NotFoundMemberException("아이디에 해당하는 멤버가 없습니다.");
        }
    }

    private List<AccountGetResponse> getAccountGetResponses(final List<Account> accounts) {
        return accounts.stream()
                       .map(AccountGetResponse::new)
                       .toList();
    }

    @Transactional
    public List<AccountTransferResponse> transfer(final AccountTransferRequest accountTransferRequest) {
        checkFriend(accountTransferRequest);
        accountTransfer(accountTransferRequest);
        accountDeposit(accountTransferRequest);

        return getAccountTransferResponses(accountTransferRequest);
    }

    private List<AccountTransferResponse> getAccountTransferResponses(final AccountTransferRequest accountTransferRequest) {
        List<Account> accounts = new ArrayList<>();

        final Account fromAccount = accountRepository.findByAccountIdAndMemberId(accountTransferRequest.getFromAccountId(),
                                                                                 accountTransferRequest.getFromMemberId());

        final Account toAccount = accountRepository.findByAccountIdAndMemberId(accountTransferRequest.getToAccountId(),
                                                                               accountTransferRequest.getToMemberId());

        accounts.add(fromAccount);
        accounts.add(toAccount);

        return accounts.stream()
                       .map(AccountTransferResponse::new)
                       .toList();
    }

    private void accountDeposit(final AccountTransferRequest accountTransferRequest) {
        final Long toMemberId = accountTransferRequest.getToMemberId();
        final Long toAccountId = accountTransferRequest.getToAccountId();
        final Long balance = accountTransferRequest.getBalance();

        final Account toAccount = accountRepository.findByAccountIdAndMemberId(toAccountId, toMemberId);
        toAccount.deposit(balance);
    }

    private void accountTransfer(final AccountTransferRequest accountTransferRequest) {
        final Long fromMemberId = accountTransferRequest.getFromMemberId();
        final Long fromAccountId = accountTransferRequest.getFromAccountId();
        final Long balance = accountTransferRequest.getBalance();

        final Account fromAccount = accountRepository.findByAccountIdAndMemberId(fromAccountId, fromMemberId);
        fromAccount.transfer(balance);
    }

    private void checkFriend(final AccountTransferRequest accountTransferRequest) {
        final Long fromId = accountTransferRequest.getFromMemberId();
        final Long toId = accountTransferRequest.getToMemberId();

        if (!friendService.existsByFriendIdAndMemberId(toId, fromId)) {
            throw new NotFriendException("친구한테만 계좌이체를 할 수 있습니다.");
        }
    }
}
