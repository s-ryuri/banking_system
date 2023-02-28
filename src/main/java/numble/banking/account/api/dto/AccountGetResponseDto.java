package numble.banking.account.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.account.application.dto.AccountGetResponse;

@Getter
@NoArgsConstructor
public class AccountGetResponseDto {
    private Long memberId;
    private Long accountId;
    private Long balance;

    public AccountGetResponseDto(AccountGetResponse accountGetResponse) {
        this.memberId = accountGetResponse.getMemberId();
        this.accountId = accountGetResponse.getAccountId();
        this.balance = accountGetResponse.getBalance();
    }
}
