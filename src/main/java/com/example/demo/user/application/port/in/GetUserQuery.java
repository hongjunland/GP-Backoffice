package com.example.demo.user.application.port.in;

import com.example.demo.user.application.port.out.response.UserResponse;

public interface GetUserQuery {
    UserResponse getUser(Long userId);
}
