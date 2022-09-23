package org.tecsharp.apiservlet.webapp.headers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Constantes {

    public static final String DB_PROPERTIES = "jdbc:mysql://localhost:3306/tecstore4?user=root&password=";
    private static String url = "jdbc:mysql://localhost:3306/tecstore4?serverTimeZone=UTC";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection () throws SQLException{

        return DriverManager.getConnection(url,username,password);

    }
}
