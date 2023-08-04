package com.example.demo.user.adapter.out.persistence;

import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = new UserMapper();
    }

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
                .id(new User.UserId(1L))
                .email("test@example.com")
                .nickname("nickname")
                .password("password")
                .name("name")
                .build();

        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);

        assertThat(userJpaEntity.getId()).isEqualTo(user.getId().getValue());
        assertThat(userJpaEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(userJpaEntity.getNickname()).isEqualTo(user.getNickname());
        assertThat(userJpaEntity.getPassword()).isEqualTo(user.getPassword());
        assertThat(userJpaEntity.getName()).isEqualTo(user.getName());
    }
}