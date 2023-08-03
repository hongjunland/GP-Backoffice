package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import com.example.demo.user.application.port.out.response.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("유저관련 조회 어댑터 테스트")
@ExtendWith(MockitoExtension.class)
public class LoginPersistenceAdapterTest {
//    @InjectMocks
//    private LoginPersistenceAdapter loginPersistenceAdapter;
    @Mock
    private SpringDataUserRepository userRepository;
//    @Mock
//    private UserMapper userMapper;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private AuthenticationManagerBuilder authBuilder;
    @Mock
    private PasswordEncoder passwordEncoder;
    @DisplayName("로그인 테스트")
    @Test
    public void login(){
        String email = "zxc123@naver.com";
        String encoded = "encoded";
        String decoded = "decoded";
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .email(email)
                .password(encoded)
                .nickname("닉네임")
                .name("홍길동")
                .build();

        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Token token = Token.builder()
                .grantToken("Bearer")
                .accessToken("accessTokenSample")
                .refreshToken("refreshTokenSample")
                .expiration(Instant.now().plusMillis(36000000))
                .build();

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(userJpaEntity));
        Mockito.when(passwordEncoder.matches(decoded, encoded)).thenReturn(true);
        Mockito.when(authBuilder.getObject()).thenReturn(authenticationManager);
        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        Mockito.when(tokenProvider.generateToken(authentication)).thenReturn(token);

        // Act
//        LoginResponse result = loginPersistenceAdapter.login(email, decoded);

        // Assert
//        assertNotNull(result);

        // Given
//        when(userRepository.findByEmail("zxc123@naver.com")).thenReturn(Optional.of(userJpaEntity));
//        when(passwordEncoder.encode(decoded)).thenReturn(encoded);
//        when(passwordEncoder.matches(decoded, encoded)).thenReturn(true);
//        AuthenticationManager authManager = mock(AuthenticationManager.class);
//        when(authBuilder.getObject()).thenReturn(Mockito.mock(AuthenticationManager.class));
//        when(authBuilder.getObject()).
//        // When
//        loginPersistenceAdapter.login(email, decoded);
//
//        // Then
//        Assertions.assertEquals(passwordEncoder.encode(decoded), encoded);
//        verify(passwordEncoder,times(1)).encode(decoded);
//        verify(passwordEncoder,times(1)).matches(decoded, encoded);

    }
}
