package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.utils.UserDetailsImpl;
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
//    private final PasswordEncoder passwordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        System.out.println("CustomDefaultOAuth2UserService -> loadUser");
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        Optional<UserJpaEntity> userJpaEntity = userRepository.findByEmail(email);
        Long userId;
        if(userJpaEntity.isEmpty()){
            UserJpaEntity newUserJpaEntity = UserJpaEntity.builder()
                    .email((String) attributes.get("email"))
                    .password("zxczxczxcxz")
                    .nickname((String) attributes.get("name"))
                    .name((String) attributes.get("given_name"))
                    .build();
            userId = userRepository.save(newUserJpaEntity).getId();
        }
        else{
            userId = userJpaEntity.get().getId();
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return UserDetailsImpl.builder()
                .username(userId.toString())
                .attributes(attributes)
                .authorities(authorityList)
                .build();
    }
}
