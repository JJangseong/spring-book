package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by marathoner on 2021/09/09
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
