package com.example.demo.user.adapter.out.persistence;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 로그에 오류 기록
        logger.error("OAuth2 authentication failed", exception);

        // 클라이언트에 오류 메시지 반환
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("OAuth2 authentication failed");
    }
}
