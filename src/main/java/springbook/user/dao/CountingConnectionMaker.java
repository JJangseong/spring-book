package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by marathoner on 2021/09/19
 */
public class CountingConnectionMaker implements ConnectionMaker{
    int counter = 0;
    private final ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
       this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
