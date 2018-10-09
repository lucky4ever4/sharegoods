package com.sharegoods.inth3rship.dao;

import com.sharegoods.inth3rship.common.Database;
import com.sharegoods.inth3rship.models.User;

import java.sql.*;

public class UserDAO
{
    public static User getUserByLoginData(String email, String password)
    {
        // Will need to add email validation here (e.g. regex or javamail api)
        // Also will call password hashing method

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE email = '" + email + "' AND password = '" + password + "' LIMIT 1");
            if (rs.next())
            {
                return new User(rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
