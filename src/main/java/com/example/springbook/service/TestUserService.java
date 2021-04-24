package com.example.springbook.service;

import com.example.springbook.TestUserServiceException;
import com.example.springbook.domain.user.User;

class TestUserService extends UserService{
    private final String id;

    public TestUserService(String id) {
        this.id = id;
    }


    @Override
    protected void upgradeLevel(User user) {
        if(user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }
}
