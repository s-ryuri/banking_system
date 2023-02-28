package numble.banking.friend.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.friend.application.FriendRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class FriendRepositoryImpl implements FriendRepository {

    private final FriendJpaRepository friendJpaRepository;

    @Override
    public List<Friend> findAllByMemberId(final Long memberId) {
        return friendJpaRepository.findAllByMemberId(memberId);
    }
}
