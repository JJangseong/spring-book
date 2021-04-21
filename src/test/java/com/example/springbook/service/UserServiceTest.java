package com.example.springbook.service;

import com.example.springbook.dao.user.UserDao;
import com.example.springbook.domain.user.Level;
import com.example.springbook.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static com.example.springbook.UserUtils.checkSameUser;
import static com.example.springbook.UserUtils.getUser;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    List<User> users;

    @BeforeEach
    public void setUp() {
        users = Arrays.asList(
                getUser("user1", Level.BASIC, 1, 0),
                getUser("user2", Level.BASIC, 50, 0),
                getUser("user3", Level.SILVER, 60, 29),
                getUser("user4", Level.SILVER, 60, 30),
                getUser("user5", Level.GOLD, 160, 130)
        );
    }

    @Test
    public void bean() {
        assertThat(this.userService, is(notNullValue()));
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for(User user: users) userDao.add(user);

        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }


    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel(), is(expectedLevel));
    }

}