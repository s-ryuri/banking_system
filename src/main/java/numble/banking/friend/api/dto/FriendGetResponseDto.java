package numble.banking.friend.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.friend.application.dto.FriendGetResponse;

@Getter
@NoArgsConstructor
public class FriendGetResponseDto {

    private Long memberId;
    private String memberName;

    public FriendGetResponseDto(final FriendGetResponse friendGetResponse) {
        this.memberId = friendGetResponse.getMemberId();
        this.memberName = friendGetResponse.getMemberName();
    }
}
