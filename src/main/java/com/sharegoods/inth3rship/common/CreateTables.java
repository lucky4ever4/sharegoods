package com.sharegoods.inth3rship.common;

import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Configuration
public class CreateTables
{
    @Bean
    CommandLineRunner initDatabase(UserRepository repository)
    {
        return args -> {
            repository.save(new User("Oxana","Lastname","oxana@gmail.com","123"));
            repository.save(new User("Ion","Lastname","ion@gmail.com","321"));
        };
    }
}
