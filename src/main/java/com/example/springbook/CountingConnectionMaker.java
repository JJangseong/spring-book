package com.example.springbook;

import com.example.springbook.dao.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DAO가 DB를 얼마나 사용하는지 파악하는 클래스
 */
public class CountingConnectionMaker implements ConnectionMaker {
    int counter = 0;
    private ConnectionMaker realConnectionMaker;

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
