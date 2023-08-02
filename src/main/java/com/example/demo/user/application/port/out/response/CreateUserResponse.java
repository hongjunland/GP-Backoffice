package com.example.demo.user.application.port.out.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
@Builder
@ToString
@Valid
public class CreateUserResponse {
    private final Long id;

    private final String name;

    private final String email;

    private final String password;

    private final String nickname;
}
