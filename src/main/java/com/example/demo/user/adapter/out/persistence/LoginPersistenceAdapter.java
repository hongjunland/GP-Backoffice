//package com.example.demo.user.adapter.out.persistence;
//
//import com.example.demo.common.annotaion.PersistenceAdapter;
//import com.example.demo.common.exception.UserBadCredentialsException;
//import com.example.demo.common.exception.UserNotFoundException;
//import com.example.demo.common.utils.Token;
//import com.example.demo.common.utils.TokenProvider;
//import com.example.demo.common.utils.UserDetailsImpl;
//import com.example.demo.user.application.port.out.LoginPort;
//import com.example.demo.user.application.port.out.response.LoginResponse;
//import com.example.demo.user.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@RequiredArgsConstructor
//@PersistenceAdapter
//class LoginPersistenceAdapter implements UserDetailsService, LoginPort {
//    private final SpringDataUserRepository userRepository;
////    private final UserMapper userMapper;
//    private final TokenProvider tokenProvider;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManagerBuilder authBuilder;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserJpaEntity userJpaEntity = userRepository.findByEmail(username)
//                    .orElseThrow(UserNotFoundException::new);
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        return UserDetailsImpl.builder()
//                .username(userJpaEntity.getId().toString())
//                .password(userJpaEntity.getPassword())
//                .authorities(authorities)
//                .build();
//    }
//
//    @Override
//    public LoginResponse login(String email, String password) {
//        UserJpaEntity userJpaEntity = userRepository.findByEmail(email)
//                .orElseThrow(UserNotFoundException::new);
//        Optional.of(passwordEncoder.matches(password, userJpaEntity.getPassword()))
//                .filter(matches -> matches)
//                .orElseThrow(UserBadCredentialsException::new);
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
//        Authentication auth = authBuilder.getObject().authenticate(authToken);
//        Token jwtToken = tokenProvider.generateToken(auth);
//
//        return LoginResponse.builder()
//                .accessToken("")
//                .refreshToken("")
//                .expiration("")
//                .build();
//    }
//}
