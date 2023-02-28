package numble.banking.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AccountJpaRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByMemberId(Long memberId);
}
