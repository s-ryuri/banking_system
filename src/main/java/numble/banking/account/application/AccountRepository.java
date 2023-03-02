package numble.banking.account.application;

import numble.banking.account.persistence.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAllByMemberId(Long memberId);

    Account findByAccountIdAndMemberId(Long accountId, Long memberId);

    void save(Account account);

    void deleteALl();
}
