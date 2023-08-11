package com.example.demo.user.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GoogleUserInfo {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String email;
    public static GoogleUserInfo of(Map<String, Object> attributes) {
        String sub = (String)attributes.get("sub");
        String name = (String) attributes.get("name");
        String givenName = (String) attributes.get("given_name");
        String familyName = (String) attributes.get("family_name");
        String picture = (String) attributes.get("picture");
        String email = (String) attributes.get("email");
        return new GoogleUserInfo(sub, name, givenName, familyName, picture, email);
    }
}
