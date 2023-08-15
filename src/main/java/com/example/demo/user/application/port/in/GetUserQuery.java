package com.example.demo.user.application.port.in;

import com.example.demo.user.adapter.in.web.response.UserResponse;

import java.util.List;

public interface GetUserQuery {
    UserResponse getUser(Long userId);

    List<UserResponse> getUserList();
}
