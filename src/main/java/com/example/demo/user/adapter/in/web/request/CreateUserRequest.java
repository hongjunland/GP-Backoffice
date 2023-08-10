package com.example.demo.user.adapter.in.web.request;

import com.example.demo.common.SelfValidating;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateUserRequest extends SelfValidating<CreateUserRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;

    public CreateUserRequest(String email, String password, String nickname, String name) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.validateSelf();
    }
}