package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.UseCase;
import com.example.demo.user.application.port.in.CreateUserUseCase;
import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.user.application.port.out.CreateUserPort;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import com.example.demo.user.application.port.out.response.CreateUserResponse;
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
    public CreateUserResponse createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .name(command.getName())
                .password(encoderPort.encode(command.getPassword()))
                .build();

        User createdUser = createUserPort.createUser(user);

        return CreateUserResponse.builder()
                .id(createdUser.getId().getValue())
                .name(createdUser.getNickname())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .password(createdUser.getPassword())
                .nickname(createdUser.getNickname())
                .build();
    }
}
