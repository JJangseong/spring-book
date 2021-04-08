package main.springbook.user;

import main.springbook.user.dao.ConnectionMaker;
import main.springbook.user.dao.DConnectionMaker;
import main.springbook.user.dao.UserDao;
import main.springbook.user.domain.User;

import java.sql.SQLException;

public class SpringBookApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("hellooo");
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
