package com.example.demo.user.application.service;

import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.UserDetailsImpl;
import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import com.example.demo.user.application.port.out.TokenGeneratorPort;
import com.example.demo.user.application.port.out.response.LoginResponse;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("로그인 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoadUserPort loadUserPort;

    @Mock
    private TokenGeneratorPort tokenGeneratorPort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private AuthenticationManagerBuilder authBuilder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setup() {
        authBuilder = Mockito.mock(AuthenticationManagerBuilder.class);
        when(authBuilder.getObject()).thenReturn(authenticationManager);
    }

    @DisplayName("로그인 테스트")
    @Test
    void loginWithValidCredentialsReturnsLoginResponse() {
        // given
        String email = "test@test.com";
        String password = "password";
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = UserDetailsImpl.builder()
                .username(Long.toString(1L))
                .password(password)
                .authorities(authorities)
                .build();
        User user = User.builder()
                .id(new User.UserId(1L))
                .email(email)
                .password(password)
                .build();
        LoginCommand command = new LoginCommand(email, password);
        Token token = Token.builder()
                .grantToken("Bearer")
                .accessToken("accessTokenSample")
                .refreshToken("refreshTokenSample")
                .expiration(Instant.now().plusMillis(36000000))
                .build();
        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(email, password);

        when(loadUserPort.loadByEmail(email)).thenReturn(user);
        when(passwordEncoderPort.matches(password, user.getPassword())).thenReturn(true);
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password))).thenReturn(mockAuthentication);
        when(tokenGeneratorPort.generateToken(mockAuthentication)).thenReturn(token);

        // when
        LoginResponse result = loginService.login(command);

        // then
        assertEquals(token.getAccessToken(), result.getAccessToken());
        assertEquals(token.getRefreshToken(), result.getRefreshToken());
        assertEquals(token.getExpiration().toString(), result.getExpiration());
    }
}