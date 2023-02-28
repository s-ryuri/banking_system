package numble.banking.friend.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
@Getter
@NoArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "friend_id", nullable = false)
    private Long friendId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    public Friend(final Long friendId, final Long memberId) {
        this.friendId = friendId;
        this.memberId = memberId;
    }
}
