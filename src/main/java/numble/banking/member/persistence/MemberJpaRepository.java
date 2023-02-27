package numble.banking.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String memberName);
}
