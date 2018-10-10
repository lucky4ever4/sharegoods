package com.sharegoods.inth3rship.common;

import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateTables
{
    @Bean
    CommandLineRunner usersSampleData(UserRepository repository)
    {
        return args -> {
            repository.save(new User("Oxana","Lastname","oxana@gmail.com","123"));
            repository.save(new User("Ion","Lastname","ion@gmail.com","asjashdl"));
        };
    }
}
