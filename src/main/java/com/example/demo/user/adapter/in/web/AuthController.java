package com.example.demo.user.adapter.in.web;


import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import com.example.demo.user.adapter.in.web.request.GoogleLoginRequest;
import com.example.demo.user.adapter.in.web.request.LoginRequest;
import com.example.demo.user.application.port.in.LoginUseCase;
import com.example.demo.user.application.port.in.command.LoginCommand;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
class AuthController {
    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public SuccessApiResponse login(@RequestBody LoginRequest loginRequest){
        LoginCommand loginCommand = LoginCommand.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
        return SuccessApiResponse.of(loginUseCase.login(loginCommand));
    }
//    @GetMapping("/user/profile")
//    public SuccessApiResponse googleLogin(@AuthenticationPrincipal OAuth2User principal) {
////        String email = principal.getAttribute("email");
////        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, "");
////        Authentication auth = authenticationManager.authenticate(authToken);
////        Token token = tokenProvider.generateToken(auth);
//        return SuccessApiResponse.of(principal.getAttributes());
//    }
}
