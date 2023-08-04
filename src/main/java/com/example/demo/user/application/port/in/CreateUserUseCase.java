package com.example.demo.user.application.port.in;

import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.user.adapter.in.web.response.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserCommand command);
}
