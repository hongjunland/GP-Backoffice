package com.example.demo.user.adapter.in.web;


import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import com.example.demo.user.application.port.in.LoginUseCase;
import com.example.demo.user.application.port.in.command.LoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
class AuthController {
    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public SuccessApiResponse login(@RequestBody LoginCommand loginCommand){
        return SuccessApiResponse.of(loginUseCase.login(loginCommand));
    }
}
