package numble.banking.member.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    private String name;

    public SignUpResponse(final String name) {
        this.name = name;
    }
}
