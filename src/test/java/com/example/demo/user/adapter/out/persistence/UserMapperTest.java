package com.example.demo.user.adapter.out.persistence;

import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("유저 Mapper 테스트")
@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;

    @Test
    void mapToDomainEntity() {
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .email("test@example.com")
                .nickname("nickname")
                .password("password")
                .name("name")
                .build();

        User user = userMapper.mapToDomainEntity(userJpaEntity);

        assertThat(user.getId().getValue()).isEqualTo(userJpaEntity.getId());
        assertThat(user.getEmail()).isEqualTo(userJpaEntity.getEmail());
        assertThat(user.getNickname()).isEqualTo(userJpaEntity.getNickname());
        assertThat(user.getPassword()).isEqualTo(userJpaEntity.getPassword());
        assertThat(user.getName()).isEqualTo(userJpaEntity.getName());
    }

    @Test
    void mapToJpaEntity() {
        User user = User.builder()
//                .id(new User.UserId(1L))
                .email("test@example.com")
                .nickname("nickname")
                .password("password")
                .name("name")
                .build();

        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);

//        assertThat(userJpaEntity.getId()).isEqualTo(user.getId().getValue());
        assertThat(userJpaEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(userJpaEntity.getNickname()).isEqualTo(user.getNickname());
        assertThat(userJpaEntity.getPassword()).isEqualTo(user.getPassword());
        assertThat(userJpaEntity.getName()).isEqualTo(user.getName());
    }
}