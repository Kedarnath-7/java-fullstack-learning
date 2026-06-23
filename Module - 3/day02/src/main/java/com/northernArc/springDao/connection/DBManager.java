package com.northernArc.springDao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/northerarc";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Error closing connection...." + e.getMessage());
            }
        }
    }
}
