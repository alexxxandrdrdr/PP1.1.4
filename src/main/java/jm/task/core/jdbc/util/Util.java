package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    protected static final String url = "jdbc:mysql://@localhost:3306/testdb";
    protected static final String driver = "com.mysql.jdbc.Driver";
    protected static final String user = "root";
    protected static final String password = "Root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Подключение успешно");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }



}
