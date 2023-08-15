package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.UseCase;
import com.example.demo.user.application.port.in.GetUserQuery;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.adapter.in.web.response.UserResponse;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoadUserService implements GetUserQuery {
    private final LoadUserPort loadUserPort;

    @Override
    public UserResponse getUser(Long userId) {
        User user = loadUserPort.loadById(userId);
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getUserList() {
        return loadUserPort.searchUserList().stream()
                .map(user -> mapToResponse(user))
                .collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId().getValue())
                .email(user.getEmail())
                .name(user.getName())
                .position(user.getPosition().getDepth())
                .build();
    }
}
