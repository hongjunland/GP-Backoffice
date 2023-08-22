package com.example.demo.user.adapter.out.persistence;

import com.example.demo.user.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("CustomDefaultOAuth2UserServiceTest 테스트")
@ExtendWith(MockitoExtension.class)
class OAuth2UserPersistenceTest {
    @InjectMocks
    private OAuth2UserPersistenceAdapter oAuth2UserPersistenceAdapter;
    @Mock
    private SpringDataUserRepository userRepository;
    @Mock
    private DefaultOAuth2UserService delegate;
    @DisplayName("OAuth user 조회 테스트- 존재하는 계정일 경우")
    @Test
    public void loadUser_existUser_test(){
        // Given
        OAuth2User oAuth2User = mock(OAuth2User.class);
        OAuth2UserRequest request = mock(OAuth2UserRequest.class);
        UserJpaEntity userJpaEntity = mock(UserJpaEntity.class);
        when(delegate.loadUser(request)).thenReturn(oAuth2User);
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userJpaEntity));

        // When
        oAuth2UserPersistenceAdapter.loadUser(request);

        // Then
        verify(userRepository, times(1)).findByEmail(any());

    }
    @DisplayName("OAuth user 조회 테스트-가입할 경우 경우")
    @Test
    public void loadUser_test(){
        // Given
        OAuth2User oAuth2User = mock(OAuth2User.class);
        OAuth2UserRequest request = mock(OAuth2UserRequest.class);
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .position(Position.EMPLOYEE.getDepth())
                .email("zxc@google.com")
                .password("zxczxczx")
                .build();
        when(delegate.loadUser(request)).thenReturn(oAuth2User);
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(userJpaEntity);
        // When
        oAuth2UserPersistenceAdapter.loadUser(request);

        // Then
        verify(userRepository, times(1)).findByEmail(any());

    }
}
