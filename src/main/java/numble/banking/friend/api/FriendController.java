package numble.banking.friend.api;

import lombok.RequiredArgsConstructor;
import numble.banking.friend.api.dto.FriendGetResponseDto;
import numble.banking.friend.application.FriendService;
import numble.banking.friend.application.dto.FriendGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/{memberId}/member")
    public ResponseEntity<?> getAllFriends(@PathVariable(name = "memberId") Long memberId) {
        final List<FriendGetResponse> allFriends = friendService.findAllFriends(memberId);

        return ResponseEntity.ok().body(toResponseDto(allFriends));
    }

    private List<FriendGetResponseDto> toResponseDto(List<FriendGetResponse> friendGetResponses) {
        return friendGetResponses.stream()
                                 .map(FriendGetResponseDto::new)
                                 .toList();
    }
}
