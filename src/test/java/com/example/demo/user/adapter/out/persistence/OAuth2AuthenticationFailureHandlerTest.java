package com.example.demo.user.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

import static org.mockito.Mockito.*;

@DisplayName("OAuth2AuthenticationFailureHandlerTest 테스트")
@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationFailureHandlerTest extends SimpleUrlAuthenticationFailureHandler {
    @Mock
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @DisplayName("onAuthenticationFailure 테스트")
    @Test
    public void onAuthenticationFailure_test() throws ServletException, IOException {
        // Given
        doNothing().when(httpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(request, response);
        // When
        oAuth2AuthenticationFailureHandler.onAuthenticationFailure(request, response, mock(AuthenticationException.class));
        // Then
        verify(httpCookieOAuth2AuthorizationRequestRepository, times(1)).removeAuthorizationRequestCookies(request, response);
    }
}

/*


@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(("/"));

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}

*
*
* */