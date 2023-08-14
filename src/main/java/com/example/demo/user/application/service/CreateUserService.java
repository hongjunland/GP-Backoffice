package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.UseCase;
import com.example.demo.user.application.port.in.CreateUserUseCase;
import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.user.application.port.out.CreateUserPort;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;
    private final PasswordEncoderPort encoderPort;

    @Override
    public void createUser(CreateUserCommand command) {
        User user = User.withoutId( command.getEmail(), encoderPort.encode(command.getPassword()),command.getName(), command.getPosition());
        createUserPort.createUser(user);
    }
}
