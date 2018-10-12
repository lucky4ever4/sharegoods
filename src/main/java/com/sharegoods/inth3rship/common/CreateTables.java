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

    User oxana;
    User ion;

    @Bean
    CommandLineRunner usersSampleData(UserRepository repository) {

        oxana = new User("Oxana", "Lastname", "oxana@gmail.com", "123");
        ion = new User("Ion", "Lastname", "ion@gmail.com", "asjashdl");

        return args -> {
            repository.save(oxana);
            repository.save(ion);
        };
    }

    @Bean
    CommandLineRunner itemsSampleData(ItemRepository repository) {
        return args -> {
            repository.save(new Item(oxana, Date.valueOf("2018-10-10"), "myTitle", "laptop"));
            repository.save(new Item(oxana, Date.valueOf("2018-10-11"), "Phone", "Lenovo p780 good condition 9/10"));
        };
    }
}
