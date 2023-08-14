package com.example.demo.user.adapter.in.web.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateUserRequest extends SelfValidating<CreateUserRequest> {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @Max(value = 4)
    @Min(value = 1)
    private int position;

    @JsonCreator
    public CreateUserRequest(@JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("name") String name, @JsonProperty("position") int position) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.position = position;
        this.validateSelf();
    }
}