package com.example.springbook;

import com.example.springbook.domain.User;

public class UserUtils {

    public static User getUser() {
        User user = new User();
        user.setId("hello13");
        user.setName("world");
        user.setPassword("1234");

        return user;
    }
}
