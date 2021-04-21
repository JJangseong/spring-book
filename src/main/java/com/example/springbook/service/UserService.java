package com.example.springbook.service;

import com.example.springbook.dao.user.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}

