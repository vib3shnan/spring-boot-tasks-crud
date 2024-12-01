package com.vibi.vibiapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private final static String url = "jdbc:mysql://localhost:3306/vibi_task";
    private final static String username = "root";
    private final static String password = "xsw2XSW@";

    public static Connection openConnection(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating DB connection");
        }
    }
}
