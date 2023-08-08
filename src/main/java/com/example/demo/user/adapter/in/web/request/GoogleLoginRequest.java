package com.example.demo.user.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleLoginRequest {
    private final String accessToken;
    private final String refreshToken;
}
