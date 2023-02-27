package numble.banking.member.application;

import numble.banking.member.application.dto.SignUpRequest;
import numble.banking.member.persistence.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberFactory {

    public Member getMember(final SignUpRequest signUpRequest) {
        return Member.builder()
                     .name(signUpRequest.getName())
                     .password(signUpRequest.getPassword())
                     .build();
    }

}
