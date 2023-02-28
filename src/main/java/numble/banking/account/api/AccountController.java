package numble.banking.account.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import numble.banking.account.api.dto.AccountGetResponseDto;
import numble.banking.account.api.dto.AccountTransferRequestDto;
import numble.banking.account.api.dto.AccountTransferResponseDto;
import numble.banking.account.application.AccountService;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.application.dto.AccountTransferRequest;
import numble.banking.account.application.dto.AccountTransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{memberId}/member")
    public ResponseEntity<?> getAccount(@PathVariable(name = "memberId") Long memberId) {

        final List<AccountGetResponse> accountGetResponses = accountService.getAccount(memberId);

        return ResponseEntity.ok().body(new Result(toGetResponseDto(accountGetResponses)));
    }

    private List<AccountGetResponseDto> toGetResponseDto(final List<AccountGetResponse> accountGetResponses) {
        return accountGetResponses.stream()
                                  .map(AccountGetResponseDto::new)
                                  .toList();
    }

    @PatchMapping("/transfer")
    public ResponseEntity<?> accountTransfer(@RequestBody @Valid AccountTransferRequestDto accountTransferRequestDto) {
        final AccountTransferRequest accountTransferRequest = accountTransferRequestDto.toRequest();

        final List<AccountTransferResponse> accountTransferResponses = accountService.transfer(accountTransferRequest);

        return ResponseEntity.ok().body(new Result(toDto(accountTransferResponses)));
    }

    private List<AccountTransferResponseDto> toDto(List<AccountTransferResponse> accountTransferResponses) {
        return accountTransferResponses.stream()
            .map(AccountTransferResponseDto::new)
            .toList();
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
