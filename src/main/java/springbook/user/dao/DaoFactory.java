package springbook.user.dao;

/**
 * Created by marathoner on 2021/09/10
 */
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(new DConnectionMaker());
    }
}
