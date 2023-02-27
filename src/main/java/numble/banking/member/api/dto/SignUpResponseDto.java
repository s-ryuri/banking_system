package numble.banking.member.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.member.application.dto.SignUpResponse;

@Getter
@NoArgsConstructor
public class SignUpResponseDto {

    private String name;

    public SignUpResponseDto(final SignUpResponse signUpResponse) {
        this.name = signUpResponse.getName();
    }
}
