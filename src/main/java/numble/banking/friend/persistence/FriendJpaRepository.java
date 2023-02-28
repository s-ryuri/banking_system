package numble.banking.friend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface FriendJpaRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByMemberId(Long memberId);

    boolean existsByFriendIdAndMemberId(Long friendId, Long memberId);
}
