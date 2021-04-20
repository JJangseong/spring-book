package com.example.springbook.dao.account;

import com.example.springbook.dao.ConnectionMaker;
import com.example.springbook.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    private ConnectionMaker connectionMaker;

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(Account account) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) value(?,?,?)"
        );
        ps.setString(1, account.getId());
        ps.setString(2, account.getName());
        ps.setString(3, account.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Account get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("" +
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Account account = new Account();
        account.setId(rs.getString("id"));
        account.setName(rs.getString("name"));
        account.setPassword(rs.getString("password"));


        rs.close();
        ps.close();
        c.close();

        return account;
    }
}
