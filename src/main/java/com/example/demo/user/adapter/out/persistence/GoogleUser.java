package com.example.demo.user.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GoogleUser {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String email;

}
