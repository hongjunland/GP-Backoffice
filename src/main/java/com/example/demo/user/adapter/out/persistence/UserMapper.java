package com.example.demo.user.adapter.out.persistence;

import com.example.demo.user.domain.Position;
import com.example.demo.user.domain.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {
    // JPA Entity => Domain Entity
    User mapToDomainEntity(UserJpaEntity userJpaEntity){
        return User.builder()
                .id(new User.UserId(userJpaEntity.getId()))
                .email(userJpaEntity.getEmail())
                .password(userJpaEntity.getPassword())
                .name(userJpaEntity.getName())
                .position(Position.fromLongValue(userJpaEntity.getPosition()))
                .build();
    }

    // Domain Entity => JPA Entity
    UserJpaEntity mapToJpaEntity(User user){
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .position(user.getPosition().getDepth())
                .build();
    }
}
