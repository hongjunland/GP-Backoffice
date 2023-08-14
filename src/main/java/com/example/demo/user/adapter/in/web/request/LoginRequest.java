package com.example.demo.user.adapter.in.web.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends SelfValidating<LoginRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("email")String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}