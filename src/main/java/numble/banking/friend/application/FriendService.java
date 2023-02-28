package numble.banking.friend.application;

import lombok.RequiredArgsConstructor;
import numble.banking.friend.application.dto.FriendAddRequest;
import numble.banking.friend.application.dto.FriendAddResponse;
import numble.banking.friend.application.dto.FriendGetResponse;
import numble.banking.friend.application.exception.DuplicatedFriendException;
import numble.banking.friend.persistence.Friend;
import numble.banking.member.application.MemberService;
import numble.banking.member.persistence.exception.NotFoundMemberException;
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
                      .map(friend -> memberService.findById(friend.getFriendId()))
                      .map(FriendGetResponse::new)
                      .toList();
    }

    public FriendAddResponse addFriend(final FriendAddRequest friendAddRequest) {
        checkMemberId(friendAddRequest.getMemberId());
        checkFriendId(friendAddRequest.getFriendId());
        checkDuplicateFriend(friendAddRequest);

        final Friend friend = makeFriend(friendAddRequest);
        final Friend saveFriend = friendRepository.save(friend);

        return new FriendAddResponse(saveFriend);
    }

    private void checkMemberId(final Long memberId) {
        if (!memberService.existsById(memberId)) {
            throw new NotFoundMemberException("아이디에 해당하는 멤버가 없습니다.");
        }
    }

    private void checkFriendId(final Long friendId) {
        if (!memberService.existsById(friendId)) {
            throw new NotFoundMemberException("아이디에 해당하는 친구 아이디가 없습니다.");
        }
    }

    private void checkDuplicateFriend(final FriendAddRequest friendAddRequest) {
        if(friendRepository.existByFriendIdAndMemberId(friendAddRequest.getFriendId(), friendAddRequest.getMemberId())) {
            throw new DuplicatedFriendException("이미 친구추가가 되어있습니다.");
        }
    }

    private Friend makeFriend(final FriendAddRequest friendAddRequest) {
        return new Friend(friendAddRequest.getFriendId(), friendAddRequest.getMemberId());
    }
}
