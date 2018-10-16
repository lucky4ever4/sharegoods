package com.sharegoods.inth3rship.common;

import com.sharegoods.inth3rship.models.Image;
import com.sharegoods.inth3rship.models.Item;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.ImageRepository;
import com.sharegoods.inth3rship.repositories.ItemRepository;
import com.sharegoods.inth3rship.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.sql.Date;

@Configuration
public class CreateTables {

    User oxana;
    User ion;
    Item laptop;

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
        laptop = new Item(oxana, Date.valueOf("2018-10-10"), "myTitle", "laptop");
        return args -> {
            repository.save(laptop);
            repository.save(new Item(oxana, Date.valueOf("2018-10-11"), "Phone", "Lenovo p780 good condition 9/10"));
        };
    }

    @Bean
    CommandLineRunner imageSampleData(ImageRepository repository)  {
        byte[] arrayImgPenguin = null;
        byte[] arrayImgGorilla = null;

        ClassPathResource penguinImage = new ClassPathResource("images/penguin.png");
        ClassPathResource gorillaImage = new ClassPathResource("images/gorilla.png");

        try {
            arrayImgPenguin = new byte[(int) penguinImage.contentLength()];
            penguinImage.getInputStream().read(arrayImgPenguin);

            arrayImgGorilla = new byte[(int) gorillaImage.contentLength()];
            gorillaImage.getInputStream().read(arrayImgGorilla);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image imagePenguin = new Image(laptop,"penguin.png", arrayImgPenguin);
        Image imageGorilla = new Image(laptop,"gorilla.png", arrayImgGorilla);

        return args -> {
            repository.save(imagePenguin);
            repository.save(imageGorilla);
        };
    }

}
