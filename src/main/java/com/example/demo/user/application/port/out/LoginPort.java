package com.example.demo.user.application.port.out;

import com.example.demo.user.adapter.in.web.response.LoginResponse;

public interface LoginPort {
    LoginResponse login(String email, String password);
}
