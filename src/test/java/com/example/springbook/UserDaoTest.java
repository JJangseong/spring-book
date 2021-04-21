package com.example.springbook;

import com.example.springbook.dao.user.UserDao;
import com.example.springbook.domain.user.Level;
import com.example.springbook.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static com.example.springbook.UserUtils.getUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao dao;
    @Autowired
    private DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {

        this.user1 = getUser("user", Level.BASIC, 1, 0);
        this.user2 = getUser("user1", Level.SILVER, 55, 10);
        this.user3 = getUser("user2", Level.GOLD, 100, 40);

//        dao = new UserDaoJdbc();
//        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/testdb", "root", "password", true);
//        dao.setDataSource(dataSource);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet1 = dao.get(user1.getId());
        checkSameUser(userGet1, user1);

        User userGet2 = dao.get(user2.getId());
        checkSameUser(userGet2, user2);

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

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

    @Test
    public void getAll() {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size(), is(0));

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user1, users3.get(0));
        checkSameUser(user2, users3.get(1));
        checkSameUser(user3, users3.get(2));
    }

    @Test
    public void duplicateKey() {
        dao.deleteAll();

        dao.add(user1);
        assertThrows(DuplicateKeyException.class, () -> dao.add(user1));
    }

    @Test
    public void update() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("Hello");
        user1.setPassword("password");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);


        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);

    }

//    @Test
//    public void sqlExceptionTranslate() {
//       dao.deleteAll();
//        try {
//            dao.add(user1);
//            dao.add(user1);
//        } catch (DuplicateKeyException e) {
//            SQLException sqlEx = (SQLException) e.getRootCause();
//            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
//
//            assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
//        }
//    }


    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }

}


