package com.example.springbook.dao.user;

import com.example.springbook.domain.user.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User get(String id);

    List<User> getAll();

    void deleteAll();

    int getCount();

    public void update(User user);
}
