package numble.banking.member.application;

import numble.banking.member.persistence.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByName(String memberName);

}
