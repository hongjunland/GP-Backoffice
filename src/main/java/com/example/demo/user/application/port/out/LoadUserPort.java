package com.example.demo.user.application.port.out;

import com.example.demo.user.domain.User;

import java.util.List;

public interface LoadUserPort {
    User loadById(Long id);
    User loadByEmail(String email);
    boolean existsByEmail(String email);
    List<User> searchUserList();
}
