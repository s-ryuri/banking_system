package numble.banking.account.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    @Override
    public List<Account> findAllByMemberId(final Long memberId) {
        return accountJpaRepository.findAllByMemberId(memberId);
    }
}
