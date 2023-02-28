package numble.banking.friend.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.friend.application.FriendRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
class FriendRepositoryImpl implements FriendRepository {

    private final FriendJpaRepository friendJpaRepository;

    @Override
    public List<Friend> findAllByMemberId(final Long memberId) {
        return friendJpaRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public Friend save(Friend friend) {
        return friendJpaRepository.save(friend);
    }

    @Override
    public boolean existByFriendIdAndMemberId(final Long friendId, final Long memberId) {
        return friendJpaRepository.existsByFriendIdAndMemberId(friendId, memberId);
    }
}
