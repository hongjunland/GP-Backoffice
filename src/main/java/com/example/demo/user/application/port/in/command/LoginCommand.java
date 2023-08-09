package com.example.demo.user.application.port.in.command;

import lombok.*;


@Builder
@AllArgsConstructor
@Getter
public class LoginCommand {
    private final String email;
    private final String password;
}
