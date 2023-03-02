package numble.banking.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import numble.banking.account.api.dto.AccountGetResponseDto;
import numble.banking.account.application.AccountService;
import numble.banking.account.application.dto.AccountGetResponse;
import numble.banking.account.persistence.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private ResultActions resultActions;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private static final String PREFIX = "/api/v1/account";
    private static final Long MEMBER_NO = 1L;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                                 .build();
    }

    @Test
    void 멤버아이디에_대한_계좌_잘_가져오는지_확인() throws Exception {
        final Account account = new Account(1L, 5000L);
        given(accountService.getAccount(anyLong()))
            .willReturn(List.of(new AccountGetResponse(account)));

        final URI uri = UriComponentsBuilder
            .fromUriString(PREFIX)
            .path("/" + MEMBER_NO)
            .path("/member")
            .build(true)
            .toUri();

        resultActions = mockMvc.perform(get(uri)
                                            .contentType(APPLICATION_JSON)
                                            .accept(APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andExpect(status().isOk())
                                                 .andReturn();

        final Result accountGetResponseDtos = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Result.class);

        assertThat(accountGetResponseDtos.data).isNotNull();
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}