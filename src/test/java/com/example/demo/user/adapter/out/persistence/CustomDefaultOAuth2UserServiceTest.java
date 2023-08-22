package com.example.demo.user.adapter.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("CustomDefaultOAuth2UserServiceTest 테스트")
@ExtendWith(MockitoExtension.class)
class CustomDefaultOAuth2UserServiceTest {
    @InjectMocks
    private CustomDefaultOAuth2UserService customService;

    @MockBean
    private DefaultOAuth2UserService defaultOAuth2UserServiceMock;

    @BeforeEach
    public void setUp() {
        // 여기에서 defaultOAuth2UserServiceMock에 대한 모킹 설정을 할 수 있습니다.
        OAuth2User mockOAuth2User = mock(OAuth2User.class);
        when(defaultOAuth2UserServiceMock.loadUser(any(OAuth2UserRequest.class)))
                .thenReturn(mockOAuth2User);
    }
    @DisplayName("")
    @Test
    public void loadUser_test(){

    }
}
