package numble.banking.account.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.account.application.dto.AccountTransferResponse;

@Getter
@NoArgsConstructor
public class AccountTransferResponseDto {
    private Long memberId;
    private Long balance;

    @Builder
    public AccountTransferResponseDto(AccountTransferResponse accountTransferResponse) {
        this.memberId = accountTransferResponse.getMemberId();
        this.balance = accountTransferResponse.getBalance();
    }
}