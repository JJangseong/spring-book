package com.example.springbook.service;

import com.example.springbook.domain.user.User;

public interface UserService {
    void add(User user);
    void upgradeLevels() throws Exception;
}
