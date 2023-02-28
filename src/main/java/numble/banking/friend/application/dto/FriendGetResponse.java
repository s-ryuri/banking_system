package numble.banking.friend.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.member.persistence.Member;

@Getter
@NoArgsConstructor
public class FriendGetResponse {

    private Long memberId;
    private String memberName;

    public FriendGetResponse(Member member) {
        this.memberId = member.getId();
        this.memberName = member.getName();
    }
}
