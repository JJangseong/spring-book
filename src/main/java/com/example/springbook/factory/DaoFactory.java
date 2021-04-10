package com.example.springbook.factory;

import com.example.springbook.dao.ConnectionMaker;
import com.example.springbook.DConnectionMaker;
import com.example.springbook.dao.account.AccountDao;
import com.example.springbook.dao.message.MessageDao;
import com.example.springbook.dao.user.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
