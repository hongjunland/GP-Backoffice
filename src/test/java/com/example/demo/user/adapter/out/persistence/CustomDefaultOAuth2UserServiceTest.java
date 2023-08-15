package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.utils.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("OAuth2 User service 테스트")
@ExtendWith(MockitoExtension.class)
public class CustomDefaultOAuth2UserServiceTest {
//    @InjectMocks
//    private CustomDefaultOAuth2UserService customDefaultOAuth2UserService;
//
//    @Mock
//    private SpringDataUserRepository userRepository;
//
//    @Test
//    public void loadUser_test(){
//        // given
//        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
//        GoogleUserInfo userInfo = GoogleUserInfo.of(oAuth2User.getAttributes());
//        Map<String, Object> attributes= new HashMap<>();
//        when(oAuth2User.getAttributes()).thenReturn(attributes);
//        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mock(UserJpaEntity.class)));
//        when(userRepository.save(any(UserJpaEntity.class))).thenReturn(mock(UserJpaEntity.class));
//
//        // when
//        UserDetailsImpl userDetails = (UserDetailsImpl) customDefaultOAuth2UserService.loadUser(mock(OAuth2UserRequest.class));
//
//        //then
//        verify(userRepository, times(1)).save(any(UserJpaEntity.class));
//        System.out.println(userDetails);
//    }
}
