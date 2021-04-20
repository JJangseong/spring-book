package com.example.springbook.dao.user;

import com.example.springbook.domain.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDao {
    void setDataSource(DataSource dataSource);

    void add(User user);

    User get(String id);

    List<User> getAll();

    void deleteAll();

    int getCount();
}
