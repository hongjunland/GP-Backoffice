package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.utils.UserDetailsImpl;
import com.example.demo.user.domain.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomDefaultOAuth2UserService extends DefaultOAuth2UserService {
    private final SpringDataUserRepository userRepository;
    // OAuth 에서 응답 받은 유저 정보 추출
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        GoogleUserInfo userInfo = GoogleUserInfo.of(oAuth2User.getAttributes());
        String email = userInfo.getEmail();
        Optional<UserJpaEntity> userJpaEntity = userRepository.findByEmail(email);
        Long userId = userJpaEntity.map(UserJpaEntity::getId)
                .orElseGet(() -> userRepository.save(mapToJpaEntity(userInfo)).getId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return UserDetailsImpl.builder()
                .username(userId.toString())
                .attributes(oAuth2User.getAttributes())
                .authorities(authorityList)
                .build();
    }
    // Google User의 속성들 -> JpaEntity
    private UserJpaEntity mapToJpaEntity(GoogleUserInfo userInfo){
        return UserJpaEntity.builder()
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .position(Position.EMPLOYEE.getDepth())
                .build();
    }
}
