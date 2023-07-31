package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.*;
import com.example.demo.common.exception.UserBadCredentialsException;
import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.UserDetailsImpl;
import com.example.demo.user.application.port.in.*;
import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.*;
import com.example.demo.user.application.port.out.response.LoginResponse;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoginService implements LoginUseCase, UserDetailsService {
    private final LoadUserPort loadUserPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationManagerBuilder authBuilder;
    @Override
    public LoginResponse login(LoginCommand command) {
        User user = loadUserPort.loadByEmail(command.getEmail());

        Optional.of(passwordEncoderPort.matches(command.getPassword(), user.getPassword()))
                .filter(matches -> matches)
                .orElseThrow(UserBadCredentialsException::new);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword());
        Authentication auth = authBuilder.getObject().authenticate(authToken);

        Token jwtToken = tokenGeneratorPort.generateToken(auth);

        return LoginResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .expiration(jwtToken.getExpiration().toString())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = loadUserPort.loadByEmail(username);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return UserDetailsImpl.builder()
                .username(user.getId().getValue().toString())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
