package numble.banking.member.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.member.application.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(final Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findById(final Long memberId) {
        return memberJpaRepository.findById(memberId);
    }

    @Override
    public Optional<Member> findByName(final String memberName) {
        return memberJpaRepository.findByName(memberName);
    }
}
