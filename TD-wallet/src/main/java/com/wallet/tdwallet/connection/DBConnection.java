package com.wallet.tdwallet.connection;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   public Connection connection;

    public DBConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    Credentials.url,
                    Credentials.user,
                    Credentials.password
            );
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
