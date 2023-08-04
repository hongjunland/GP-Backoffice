//package com.example.demo.user.adapter.in.web.request;
//
//import com.example.demo.common.SelfValidating;
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//
//@Value
//@EqualsAndHashCode(callSuper = false)
//public class LoginRequest extends SelfValidating<LoginRequest> {
//    @Email
//    private final String email;
//    @NotBlank
//    private final String password;
//
//    public LoginRequest(String email, String password) {
//        this.email = email;
//        this.password = password;
//        this.validateSelf();
//    }
//}