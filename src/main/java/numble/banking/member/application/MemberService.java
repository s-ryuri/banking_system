package numble.banking.member.application;

import lombok.RequiredArgsConstructor;
import numble.banking.member.application.dto.SignUpRequest;
import numble.banking.member.application.dto.SignUpResponse;
import numble.banking.member.application.exception.DuplicatedNameException;
import numble.banking.member.persistence.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberFactory memberFactory;

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public SignUpResponse signUp(final SignUpRequest signUpRequest) {
        checkDuplicatedName(signUpRequest);
        return saveMember(signUpRequest);
    }

    private SignUpResponse saveMember(final SignUpRequest signUpRequest) {
        final Member member = memberFactory.getMember(signUpRequest);
        final Member saveMember = memberRepository.save(member);

        return new SignUpResponse(saveMember.getName());
    }

    private void checkDuplicatedName(final SignUpRequest signUpRequest) {
        if (memberRepository.existByName(signUpRequest.getName())) {
            throw new DuplicatedNameException("아이디가 중복되었습니다.");
        }
    }

    public boolean existsById(final Long memberId) {
        return memberRepository.existById(memberId);
    }
}
