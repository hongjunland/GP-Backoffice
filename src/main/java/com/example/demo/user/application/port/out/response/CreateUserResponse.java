package com.example.demo.user.application.port.out.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CreateUserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String nickname;
}
