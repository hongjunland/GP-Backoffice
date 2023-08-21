package com.example.demo.user.adapter.out.persistence;


import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("OAuth2AuthenticationSuccessHandlerTest 테스트")
@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationSuccessHandlerTest{
    @InjectMocks
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;

    @DisplayName("onAuthenticationSuccess 성공 테스트")
    @Test
    public void onAuthenticationSuccess_test() throws IOException, ServletException {
        // Given
        Token token = Token.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .expiration(Instant.now())
                .build();
        when(tokenProvider.generateToken(any())).thenReturn(token);
        when(response.isCommitted()).thenReturn(false);
        // When
        oAuth2AuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Then
        verify(tokenProvider, times(1)).generateToken(any());
        verify(httpCookieOAuth2AuthorizationRequestRepository, times(1)).removeAuthorizationRequestCookies(request, response);
    }
    @DisplayName("onAuthenticationSuccess 실패 테스트")
    @Test
    public void onAuthenticationSuccess_failure_test() throws IOException, ServletException {
        // Given
        Token token = Token.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .expiration(Instant.now())
                .build();
        when(tokenProvider.generateToken(any())).thenReturn(token);
        when(response.isCommitted()).thenReturn(true);
        // When
        oAuth2AuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Then
        verify(tokenProvider, times(1)).generateToken(any());
        verify(httpCookieOAuth2AuthorizationRequestRepository, times(0)).removeAuthorizationRequestCookies(request, response);
    }
}
