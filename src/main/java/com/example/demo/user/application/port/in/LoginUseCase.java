package com.example.demo.user.application.port.in;


import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);
}
