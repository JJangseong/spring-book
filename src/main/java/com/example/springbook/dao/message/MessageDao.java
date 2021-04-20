package com.example.springbook.dao.message;

import com.example.springbook.dao.ConnectionMaker;
import com.example.springbook.domain.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao {
    private ConnectionMaker connectionMaker;

    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(Message message) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) value(?,?,?)"
        );
        ps.setString(1, message.getId());
        ps.setString(2, message.getName());
        ps.setString(3, message.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Message get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("" +
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Message message = new Message();
        message.setId(rs.getString("id"));
        message.setName(rs.getString("name"));
        message.setPassword(rs.getString("password"));


        rs.close();
        ps.close();
        c.close();

        return message;
    }
}
