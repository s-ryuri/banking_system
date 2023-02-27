package numble.banking.member.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.member.application.dto.SignUpRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "[8,16]")
    private String password;

    public SignUpRequest toSignUpRequest() {

    }

}
