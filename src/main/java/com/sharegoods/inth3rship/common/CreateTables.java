package com.sharegoods.inth3rship.common;

import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import com.sharegoods.inth3rship.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Configuration
public class CreateTables {
    @Bean
    CommandLineRunner usersSampleData(UserRepository repository) {
        return args -> {
            repository.save(new User("Oxana", "Lastname", "oxana@gmail.com", "123"));
            repository.save(new User("Ion", "Lastname", "ion@gmail.com", "asjashdl"));
        };
    }

    @Bean
    CommandLineRunner itemsSampleData(ItemRepository repository) {
        return args -> {
            repository.save(new Item(1L, Date.valueOf("2018-10-10"), "myTitle", "laptop"));
            repository.save(new Item(2L, Date.valueOf("2018-10-11"), "Phone", "Lenovo p780 good condition 9/10"));
        };
    }
}
