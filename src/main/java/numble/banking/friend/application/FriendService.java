package numble.banking.friend.application;

import lombok.RequiredArgsConstructor;
import numble.banking.friend.application.dto.FriendGetResponse;
import numble.banking.friend.persistence.Friend;
import numble.banking.member.application.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    private final MemberService memberService;

    public List<FriendGetResponse> findAllFriends(final Long memberId) {
        final List<Friend> friends = friendRepository.findAllByMemberId(memberId);
        return showMyFriend(friends);
    }

    private List<FriendGetResponse> showMyFriend(final List<Friend> friends) {
        return friends.stream()
                      .map(friend -> memberService.findById(friend.getMemberId()))
                      .map(FriendGetResponse::new)
                      .toList();
    }
}
