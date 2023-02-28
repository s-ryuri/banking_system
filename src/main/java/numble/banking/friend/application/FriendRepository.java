package numble.banking.friend.application;

import numble.banking.friend.persistence.Friend;

import java.util.List;

public interface FriendRepository {

    List<Friend> findAllByMemberId(Long memberId);

}
