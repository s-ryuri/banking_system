package numble.banking.account.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.AccountRepository;
import numble.banking.account.persistence.exception.NotFoundAccountException;
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

    @Override
    public Account findByAccountIdAndMemberId(final Long accountId, final Long memberId) {
        return accountJpaRepository.findByIdAndMemberId(accountId, memberId)
                                   .orElseThrow(() -> new NotFoundAccountException("해당하는 계좌가 없습니다."));
    }
}
