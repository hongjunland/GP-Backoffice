//package com.example.demo.user.application.service;
//
//import com.example.demo.user.application.port.in.CreateUserUseCase;
//import com.example.demo.user.application.port.in.command.CreateUserCommand;
//import com.example.demo.user.application.port.out.CreateUserPort;
//import com.example.demo.user.application.port.out.PasswordEncoderPort;
//import com.example.demo.user.application.port.out.response.CreateUserResponse;
//import com.example.demo.user.domain.User;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//class CreateUserServiceTest {
//    @Mock
//    private CreateUserPort createUserPort;
//
//    @Mock
//    private PasswordEncoderPort encoderPort;
//
//    @InjectMocks
//    private CreateUserService createUserService;
//
//    private CreateUserCommand command;
//    private User user;
//    private CreateUserResponse response;
//
//    @BeforeEach
//    public void setup() {
//        command = new CreateUserCommand("testEmail@test.com", "testPassword", "testName", "testNickname");
//        user = User.builder()
//                .id(new User.UserId(1L))
//                .email(command.getEmail())
//                .nickname(command.getNickname())
//                .name(command.getName())
//                .password(command.getPassword())
//                .build();
//        response = CreateUserResponse.builder()
//                .id(1L)
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .name(user.getName())
//                .nickname(user.getNickname())
//                .build();
//
//        when(encoderPort.encode(command.getPassword())).thenReturn("encodedPassword");
//        when(createUserPort.createUser(user)).thenReturn(user);
//    }
//
//    @Test
//    public void createUserTest() {
//        CreateUserResponse actualResponse = createUserService.createUser(command);
//
//        verify(encoderPort, times(1)).encode(command.getPassword());
//        verify(createUserPort, times(1)).createUser(user);
//        assertEquals(response.getEmail(), actualResponse.getEmail());
//        assertEquals(response.getPassword(), actualResponse.getPassword());
//        assertEquals(response.getName(), actualResponse.getName());
//        assertEquals(response.getNickname(), actualResponse.getNickname());
//    }
//}
