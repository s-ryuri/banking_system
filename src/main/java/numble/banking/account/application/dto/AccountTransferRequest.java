package numble.banking.account.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountTransferRequest {

    private Long fromAccountId;
    private Long fromMemberId;

    private Long toAccountId;
    private Long toMemberId;

    private Long balance;

    @Builder
    public AccountTransferRequest(final Long fromAccountId,
                                  final Long fromMemberId,
                                  final Long toAccountId,
                                  final Long toMemberId,
                                  final Long balance) {
        this.fromAccountId = fromAccountId;
        this.fromMemberId = fromMemberId;
        this.toAccountId = toAccountId;
        this.toMemberId = toMemberId;
        this.balance = balance;
    }
}
