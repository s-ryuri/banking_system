package numble.banking.account.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.account.persistence.Account;

@Getter
@NoArgsConstructor
public class AccountTransferResponse {
    private Long memberId;
    private Long balance;

    public AccountTransferResponse(Account account) {
        this.memberId = account.getMemberId();
        this.balance = account.getBalance();
    }
}
