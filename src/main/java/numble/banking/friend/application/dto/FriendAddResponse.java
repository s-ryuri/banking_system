package numble.banking.friend.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.friend.persistence.Friend;

@Getter
@NoArgsConstructor
public class FriendAddResponse {

    private Long friendId;

    private Long memberId;

    public FriendAddResponse(Friend friend) {
        this.friendId = friend.getFriendId();
        this.memberId = friend.getMemberId();
    }
}
