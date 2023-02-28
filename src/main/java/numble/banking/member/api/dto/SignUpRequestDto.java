package numble.banking.member.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.member.application.dto.SignUpRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "아이디는 빈칸이면 안됩니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,10}+$", message = "숫자와 영문자만 입력해주세요, 5~10글자 입력")
    private String name;

    @NotBlank(message = "패스워드는 빈칸이면 안됩니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}+$", message = "숫자와 영문자만 입력해주세요, 8~16글자 입력")
    private String password;
    public SignUpRequest toSignUpRequest() {
        return SignUpRequest.builder()
                            .name(this.name)
                            .password(this.password)
                            .build();
    }

}
