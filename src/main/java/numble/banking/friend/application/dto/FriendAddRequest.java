package numble.banking.friend.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendAddRequest {

    private Long friendId;

    private Long memberId;

    public FriendAddRequest(final Long friendId, final Long memberId) {
        this.friendId = friendId;
        this.memberId = memberId;
    }
}
