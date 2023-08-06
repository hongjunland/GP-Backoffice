package com.example.demo.user.adapter.in.web.request;

import com.example.demo.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest extends SelfValidating<LoginRequest> {
    @Email
    private final String email;
    @NotBlank
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}