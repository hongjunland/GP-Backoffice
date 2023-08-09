package com.example.demo.user.adapter.in.web.request;

import com.example.demo.common.SelfValidating;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest extends SelfValidating<LoginRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}