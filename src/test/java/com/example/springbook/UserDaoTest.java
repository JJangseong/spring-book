package com.example.springbook;

import com.example.springbook.dao.ConnectionMaker;
import com.example.springbook.dao.DConnectionMaker;
import com.example.springbook.dao.user.UserDao;
import com.example.springbook.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("hello!");
        user.setName("world");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getId());
        System.out.println(user2.getPassword() + " 조회 성공");
    }
}
