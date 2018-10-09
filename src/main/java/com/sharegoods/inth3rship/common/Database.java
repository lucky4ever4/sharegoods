package com.sharegoods.inth3rship.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:sharegoods;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection()
    {
        Connection dbConnection = null;
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dbConnection;
    }


}
