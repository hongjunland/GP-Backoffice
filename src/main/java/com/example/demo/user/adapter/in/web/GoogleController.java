package com.example.demo.user.adapter.in.web;

import com.example.demo.common.annotaion.WebAdapter;
import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GoogleController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<?> googleLogin(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, "");
        Authentication auth = authenticationManager.authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        Authentication authentication = authenticationManager.authenticate(authToken);
        String jwt = tokenProvider.generateToken(authToken);
        return ResponseEntity.ok(jwt);
    }
}
