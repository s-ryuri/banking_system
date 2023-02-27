package numble.banking.member.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SignUpRequest {

    private final String name;
    private final String password;

    @Builder
    public SignUpRequest(final String name, final String password) {
        this.name = name;
        this.password = password;
    }
}
