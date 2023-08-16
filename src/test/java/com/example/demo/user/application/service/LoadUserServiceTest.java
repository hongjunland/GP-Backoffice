package com.example.demo.user.application.service;

import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.adapter.in.web.response.UserResponse;
import com.example.demo.user.domain.Position;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("유저 조회관련 서비스")
@ExtendWith(SpringExtension.class)
class LoadUserServiceTest {
    @InjectMocks
    private LoadUserService loadUserService;
    @Mock
    private LoadUserPort loadUserPort;
    @DisplayName("유저 id 기반 단일조회 테스트")
    @Test
    public void getUser_test(){
        // Given
        Long userId = 1L;
        User user = User.builder()
                .id(new User.UserId(userId))
                .email("zxc123@naver.com")
                .password("encodedPassword")
                .position(Position.EMPLOYEE)
                .name("홍길동")
                .build();
        when(loadUserPort.loadById(userId)).thenReturn(user);

        // When
        UserResponse userResponse = loadUserService.getUser(userId);

        //then
        verify(loadUserPort, times(1)).loadById(1L);
        Assertions.assertEquals(userId, userResponse.getId());
    }

    @DisplayName("유저목록 검색 테스트")
    @Test
    public void getUserList_test(){
        // Given
        List<User> userList = Arrays.asList(
                User.withId(new User.UserId(1L),"zxc123@naver.com","dasdsa","홍길동",Position.EMPLOYEE.getDepth()),
                User.withId(new User.UserId(2L),"zxc12@naver.com","dasdsa","김길동",Position.EXECUTIVE.getDepth()),
                User.withId(new User.UserId(3L),"zxc1243@naver.com","dasdsa","박길동",Position.EMPLOYEE.getDepth())
        );
        when(loadUserPort.searchUserList()).thenReturn(userList);

        // When
        List<UserResponse> userResponseList = loadUserService.getUserList();
        // Then
        verify(loadUserPort, times(1)).searchUserList();
    }


}
