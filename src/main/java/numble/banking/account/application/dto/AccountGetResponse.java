package numble.banking.account.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.account.persistence.Account;

@Getter
@NoArgsConstructor
public class AccountGetResponse {

    private Long memberId;
    private Long accountId;
    private Long balance;

    public AccountGetResponse(Account account) {
        this.memberId = account.getMemberId();
        this.accountId = account.getId();
        this.balance = account.getBalance();
    }
}
