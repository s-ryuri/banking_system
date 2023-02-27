package numble.banking.member.api;

import lombok.RequiredArgsConstructor;
import numble.banking.member.api.dto.SignUpRequestDto;
import numble.banking.member.api.dto.SignUpResponseDto;
import numble.banking.member.application.MemberService;
import numble.banking.member.application.dto.SignUpRequest;
import numble.banking.member.application.dto.SignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        final SignUpRequest signUpRequest = signUpRequestDto.toSignUpRequest();
        final SignUpResponse signUpResponse = memberService.signUp(signUpRequest);

        return ResponseEntity.ok().body(new SignUpResponseDto((signUpResponse)));
    }

}
