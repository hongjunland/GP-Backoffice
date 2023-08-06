package com.example.demo.user.adapter.in.web;

import com.example.demo.common.SuccessApiResponse;
import com.example.demo.user.adapter.in.web.response.LoginResponse;
import com.example.demo.user.application.port.in.LoginUseCase;
import com.example.demo.user.application.port.in.command.LoginCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("인증 컨트롤러 테스트")
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private LoginUseCase loginUseCase;

    @DisplayName("로그인 테스트")
    @Test
    public void loginTest(){
        // Given
        LoginCommand loginCommand = LoginCommand.builder()
                .email("zxc123@naver.com")
                .password("zxczxczxc")
                .build();
        LoginResponse loginResponse = Mockito.mock(LoginResponse.class);
        Mockito.when(loginUseCase.login(loginCommand)).thenReturn(loginResponse);

        // when
        SuccessApiResponse successApiResponse = authController.login(loginCommand);

        // then
        Mockito.verify(loginUseCase, Mockito.times(1)).login(loginCommand);
        Assertions.assertThat(successApiResponse.getStatus())
                .isEqualTo(200);
    }
}
