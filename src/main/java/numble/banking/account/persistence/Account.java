package numble.banking.account.persistence;

import lombok.Builder;
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
@Table(name = "account")
@Getter
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long balance;

    @Builder
    public Account(final Long memberId, final Long balance) {
        this.memberId = memberId;
        this.balance = balance;
    }
}
