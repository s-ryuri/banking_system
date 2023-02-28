package numble.banking.member.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.member.application.exception.DuplicatedNameException;
import numble.banking.member.persistence.exception.NotFoundMemberException;
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
    public Member findById(final Long memberId) {
        return memberJpaRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundMemberException("아이디에 해당하는 멤버가 없습니다."));
    }

    @Override
    public Member findByName(final String memberName) {
        return memberJpaRepository.findByName(memberName)
            .orElseThrow(() -> new NotFoundMemberException("이름에 해당하는 멤버가 없습니다."));
    }

    @Override
    public boolean existByName(final String memberName) {
        return memberJpaRepository.existsByName(memberName);
    }
}
