package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Token token = tokenProvider.generateToken(authentication);
        response.addHeader("Authorization", "Bearer " + token.getAccessToken());

        // 필요에 따라 응답 본문이나 쿠키에 토큰을 추가할 수도 있습니다.
        // 여기서는 헤더에 토큰을 추가했습니다.

        clearAuthenticationAttributes(request);
    }
}
