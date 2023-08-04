package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.*;
import com.example.demo.common.exception.UserBadCredentialsException;
import com.example.demo.common.utils.Token;
import com.example.demo.user.application.port.in.*;
import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.*;
import com.example.demo.user.adapter.in.web.response.LoginResponse;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoginService implements LoginUseCase {
    private final LoadUserPort loadUserPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginCommand command) {
        User user = loadUserPort.loadByEmail(command.getEmail());
        Optional.of(passwordEncoderPort.matches(command.getPassword(), user.getPassword()))
                .filter(matches -> matches)
                .orElseThrow(UserBadCredentialsException::new);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        Token jwtToken = tokenGeneratorPort.generateToken(auth);
        return LoginResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .expiration(jwtToken.getExpiration().toString())
                .build();
    }
}
