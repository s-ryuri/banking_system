package numble.banking.account.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import numble.banking.account.api.dto.AccountGetResponseDto;
import numble.banking.account.application.AccountService;
import numble.banking.account.application.dto.AccountGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Data
    @AllArgsConstructor
    static class Result<T> {

        //이런식으로 감싸줘야된다.
        private T data;
    }
}
