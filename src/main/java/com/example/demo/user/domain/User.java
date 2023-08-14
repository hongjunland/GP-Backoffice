package com.example.demo.user.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    private final UserId id;
    private final String email;
    private final String password;
    private final String name;
    private final Position position;
    public static User withId(User.UserId userId, String email, String password, String name, int positionDepth){
        return new User(userId, email, password, name, Position.fromLongValue(positionDepth));
    }
    public static User withoutId(String email, String password, String name, int positionDepth){
        return new User(null, email, password, name, Position.fromLongValue(positionDepth));
    }
//    public Optional<UserId> getId(){
//        return Optional.ofNullable(this.id);
//    }
    @Value
    public static class UserId {
        private final Long value;
    }
}
