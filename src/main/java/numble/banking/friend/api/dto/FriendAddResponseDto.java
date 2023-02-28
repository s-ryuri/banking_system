package numble.banking.friend.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.friend.application.dto.FriendAddResponse;

@Getter
@NoArgsConstructor
public class FriendAddResponseDto {

    private Long friendId;

    private Long memberId;

    public FriendAddResponseDto(FriendAddResponse friendAddResponse) {
        this.friendId = friendAddResponse.getFriendId();
        this.memberId = friendAddResponse.getMemberId();
    }
}
