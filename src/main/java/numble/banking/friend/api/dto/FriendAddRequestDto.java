package numble.banking.friend.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.friend.application.dto.FriendAddRequest;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class FriendAddRequestDto {

    @NotNull(message = "친구 아이디 값은 빈칸이면 안됩니다.")
    private Long friendId;

    @NotNull(message = "본인 아이디 값은 빈칸이면 안됩니다.")
    private Long memberId;

    public FriendAddRequest toAddRequest() {
        return new FriendAddRequest(this.friendId, this.memberId);
    }
}
