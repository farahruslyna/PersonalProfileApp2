package com.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/student_profiles?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "";

        return DriverManager.getConnection(url, username, password);
    }
}