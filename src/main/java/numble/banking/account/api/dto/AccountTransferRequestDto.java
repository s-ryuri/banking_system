package numble.banking.account.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.banking.account.application.dto.AccountTransferRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountTransferRequestDto {

    @NotNull(message = "송금하는 사람의 계좌 아이디를 입력해주세요")
    private Long fromAccountId;
    @NotNull(message = "송금하는 사람의 멤버 아이디를 입력해주세요")
    private Long fromMemberId;

    @NotNull(message = "송금받는 사람의 계좌 아이디를 입력해주세요")
    private Long toAccountId;

    @NotNull(message = "송금받는 사람의 멤버 아이디를 입력해주세요")
    private Long toMemberId;

    @Min(value = 0, message = "금액은 0보다 커야됩니다.")
    @NotNull(message = "이체 금액을 입력해주세요")
    private Long balance;

    public AccountTransferRequest toRequest() {
        return AccountTransferRequest.builder()
            .fromAccountId(this.fromAccountId)
            .fromMemberId(this.fromMemberId)
            .toAccountId(this.toAccountId)
            .toMemberId(this.toMemberId)
            .balance(this.balance)
            .build();
    }
}
