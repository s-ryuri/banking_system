package numble.banking.member.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.member.application.dto.SignUpRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{5,10}+$", message = "숫자와 영문자만 입력해주세요")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}+$", message = "숫자와 영문자만 입력해주세요")
    private String password;

    public SignUpRequest toSignUpRequest() {
        return SignUpRequest.builder()
                            .name(this.name)
                            .password(this.password)
                            .build();
    }

}
