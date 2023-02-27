package numble.banking.member.application;

import lombok.RequiredArgsConstructor;
import numble.banking.member.application.dto.SignUpRequest;
import numble.banking.member.application.exception.DuplicatedNameException;
import numble.banking.member.persistence.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberFactory memberFactory;

    public void signUp(final SignUpRequest signUpRequest) {
        checkDuplicatedName(signUpRequest);
        saveMember(signUpRequest);
    }

    private void saveMember(final SignUpRequest signUpRequest) {
        final Member member = memberFactory.getMember(signUpRequest);
        memberRepository.save(member);
    }

    private void checkDuplicatedName(final SignUpRequest signUpRequest) {
        memberRepository.findByName(signUpRequest.getName())
                        .orElseThrow(() -> new DuplicatedNameException("아이디가 중복되었습니다."));
    }
}
