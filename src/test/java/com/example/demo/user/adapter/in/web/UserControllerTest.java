package com.example.demo.user.adapter.in.web;

import com.example.demo.common.SuccessApiResponse;
import com.example.demo.user.application.port.in.CreateUserUseCase;
import com.example.demo.user.application.port.in.GetUserQuery;
import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.user.application.port.out.response.CreateUserResponse;
import com.example.demo.user.application.port.out.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private GetUserQuery getUserQuery;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void shouldCreateUser_whenPostRequest(){
        // Given
        CreateUserCommand userCommand = new CreateUserCommand("zxc123", "비밀번호1","이름1","별명1");
        CreateUserResponse createUserResponse = CreateUserResponse.builder()
                .id(1L)
                .email("zxc123@naver.com")
                .password("비밀번호1")
                .name("이름1")
                .nickname("별명1")
                .build();

        when(createUserUseCase.createUser(userCommand)).thenReturn(createUserResponse);

        // When
        SuccessApiResponse response = userController.createUser(userCommand);

        // Then
        verify(createUserUseCase, times(1)).createUser(userCommand);
        assertEquals(createUserResponse, response.getData());
    }
    @Test
    public void shouldGetUser_whenGetRequest(){
        // Given
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .email("zxc123@naver.com")
                .password("비밀번호1")
                .name("이름1")
                .nickname("별명1")
                .build();
        when(getUserQuery.getUser(1L)).thenReturn(userResponse);

        //When
        SuccessApiResponse response = userController.getUserById(1L);
        verify(getUserQuery, times(1)).getUser(1L);
        assertEquals(userResponse, response.getData());

    }
    @Test
    public void shouldGetCurrentUser_whenGetRequest(){
        // Given
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .email("zxc123@naver.com")
                .password("비밀번호1")
                .name("이름1")
                .nickname("별명1")
                .build();
        when(getUserQuery.getUser(1L)).thenReturn(userResponse);

        // When
        SuccessApiResponse response = userController.getCurrentUser(1L);

        // Then
        verify(getUserQuery, times(1)).getUser(1L);
        assertEquals(userResponse, response.getData());

    }
}
