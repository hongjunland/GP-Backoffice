package com.example.demo.user.adapter.in.web;

import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.CurrentUser;
import com.example.demo.user.adapter.in.web.request.CreateUserRequest;
import com.example.demo.user.application.port.in.CreateUserUseCase;
import com.example.demo.user.application.port.in.GetUserQuery;
import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserQuery getUserQuery;
    @PostMapping
    public SuccessApiResponse<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .position(createUserRequest.getPosition())
                .password(createUserRequest.getPassword())
                .build();
        createUserUseCase.createUser(createUserCommand);
        return SuccessApiResponse.of();
    }
    // 목록 조회
    @GetMapping
    public SuccessApiResponse<?> getUserList(){
        return SuccessApiResponse.of(getUserQuery.getUserList());
    }
    // 자기자신
    @GetMapping("/profile")
    public SuccessApiResponse<?> getCurrentUser(@CurrentUser Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }
    // 프로필 단일조회
    @GetMapping("/profile/{userId}")
    public SuccessApiResponse<?> getUserByUserId(@PathVariable Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }

}
