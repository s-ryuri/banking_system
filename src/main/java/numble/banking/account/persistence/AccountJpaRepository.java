package numble.banking.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AccountJpaRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByMemberId(Long memberId);

    Optional<Account> findByIdAndMemberId(Long accountId, Long memberId);
}
