package com.wallet.tdwallet.connection;
import org.springframework.beans.factory.annotation.Value;

import lombok.*;

import java.sql.DriverManager;
import java.sql.SQLException;
public class Connection {
    @Value("${spring.datasource.url")
    private String url;
    @Value("${spring.datasource.user}")
    private String user;
    @Value("${spring.datasource.password")
    private String password;

    public java.sql.Connection connect() {
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
