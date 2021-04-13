package com.example.springbook;

import com.example.springbook.dao.user.UserDao;
import com.example.springbook.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static com.example.springbook.UserUtils.getUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {
    private UserDao dao;

    @BeforeEach
    public void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test()
    public void addAndGet() throws SQLException, ClassNotFoundException {
        User user1 = getUser("user");
        User user2 = getUser("user1");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet1 = dao.get(user1.getId());
        assertThat(user1.getName(), is(userGet1.getName()));
        assertThat(user1.getPassword(), is(userGet1.getPassword()));

        User userGet2 = dao.get(user2.getId());
        assertThat(user1.getName(), is(userGet2.getName()));
        assertThat(user1.getPassword(), is(userGet2.getPassword()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        User user1 = getUser("user");
        User user2 = getUser("user1");
        User user3 = getUser("user2");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("null"));
    }
}
