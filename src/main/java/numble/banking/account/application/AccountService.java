package numble.banking.account.application;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.persistence.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<AccountGetResponse> getAccount(Long memberId) {
        final List<Account> accounts = accountRepository.findAllByMemberId(memberId);

        return getAccountGetResponses(accounts);
    }

    private List<AccountGetResponse> getAccountGetResponses(final List<Account> accounts) {
        return accounts.stream()
                       .map(AccountGetResponse::new)
                       .toList();
    }
}
