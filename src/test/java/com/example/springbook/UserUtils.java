package com.example.springbook;

import com.example.springbook.domain.User;

public class UserUtils {

    public static User getUser() {
        return new User()
                .builder()
                .id("userId1111")
                .name("USER_TEST")
                .password("PASSWORD")
                .build();
    }

    public static User getUser(String id) {
        return new User()
                .builder()
                .id(id)
                .name("USER_TEST")
                .password("PASSWORD")
                .build();
    }
}
