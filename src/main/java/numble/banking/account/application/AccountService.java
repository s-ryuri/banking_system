package numble.banking.account.application;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.persistence.Account;
import numble.banking.member.application.MemberService;
import numble.banking.member.persistence.exception.NotFoundMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<AccountGetResponse> getAccount(Long memberId) {
        checkMemberId(memberId);

        final List<Account> accounts = accountRepository.findAllByMemberId(memberId);

        return getAccountGetResponses(accounts);
    }

    private void checkMemberId(Long memberId) {
        if(!memberService.existsById(memberId)) {
            throw new NotFoundMemberException("아이디에 해당하는 멤버가 없습니다.");
        }
    }

    private List<AccountGetResponse> getAccountGetResponses(final List<Account> accounts) {
        return accounts.stream()
                       .map(AccountGetResponse::new)
                       .toList();
    }
}
