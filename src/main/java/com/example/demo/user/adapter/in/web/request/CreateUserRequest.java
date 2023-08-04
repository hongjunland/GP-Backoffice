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
//public class CreateUserRequest extends SelfValidating<CreateUserRequest> {
//    @Email
//    private final String email;
//    @NotBlank
//    private final String password;
//    @NotBlank
//    private final String nickname;
//    @NotBlank
//    private final String name;
//
//    public CreateUserRequest(String email, String password, String nickname, String name) {
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.name = name;
//        this.validateSelf();
//    }
//}