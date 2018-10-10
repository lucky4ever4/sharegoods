package com.sharegoods.inth3rship.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateTables
{

    public static void GenerateTables()
    {

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS Users (Id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), email VARCHAR(255) UNIQUE, password VARCHAR(255))");

            // Sample data for testing
            String email = "ion@gmail.com";
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE email = '" + email + "'");
            if(!rs.next())
            {
                statement.execute("INSERT INTO Users (first_name, last_name, email, password) VALUES ('Ion','Isac','" + email + "','123')");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
