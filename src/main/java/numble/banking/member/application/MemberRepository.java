package numble.banking.member.application;

import numble.banking.member.persistence.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long memberId);

    Member findByName(String memberName);

    boolean existByName(String memberName);

    boolean existById(Long memberId);

    void deleteAll();
}
