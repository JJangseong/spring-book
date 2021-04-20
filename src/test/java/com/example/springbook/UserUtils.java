package com.example.springbook;

import com.example.springbook.domain.user.Level;
import com.example.springbook.domain.user.User;

public class UserUtils {

    public static User getUser() {
        return new User()
                .builder()
                .id("userId1111")
                .name("USER_TEST")
                .password("PASSWORD")
                .level(Level.BASIC)
                .login(1)
                .recommend(0)
                .build();
    }

    public static User getUser(String id, Level level, int login, int recommend) {
        return new User()
                .builder()
                .id(id)
                .name("USER_TEST")
                .password("PASSWORD")
                .level(level)
                .login(login)
                .recommend(recommend)
                .build();
    }
}
