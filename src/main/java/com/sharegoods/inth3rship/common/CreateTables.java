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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.core.io.ClassPathResource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateTables {

    List<User> users = new ArrayList<>();
    List<Item> items = new ArrayList<>();
//    User oxana;
//    User ion;
//    Item laptop;

    @Bean
    CommandLineRunner usersSampleData(UserRepository repository) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.users.add(new User("Oxana", "Dunav", "oxana.dunav@gmail.com", passwordEncoder.encode("123")));
        this.users.add(new User("Arthur", "Fuentes", "afuentes1@gmail.com", passwordEncoder.encode("EFFiS3AfTYGr")));
        this.users.add(new User("Angele", "Wieprecht", "awieprecht3@gmail.com", passwordEncoder.encode("mi5Mix4vnufR")));
        this.users.add(new User("Fidelia", "Chaplin", "fchaplin4@gmail.com", passwordEncoder.encode("9zPwfaojba")));
        this.users.add(new User("Meridith", "Linfitt", "mlinfitt5@gmail.com", passwordEncoder.encode("mGHZTxifOq1")));
        this.users.add(new User("Laureen", "Neilan", "lneilan6@gmail.com", passwordEncoder.encode("W6xrMXTLdCu")));
        this.users.add(new User("Prudi", "Lillistone", "plillistone7@gmail.com", passwordEncoder.encode("Z4wpZg9zTIKj")));
        this.users.add(new User("Junie", "McVeagh", "jmcveagh8@gmail.com", passwordEncoder.encode("fuf8o3wIbH4")));
        this.users.add(new User("Laurel", "Goldspink", "lgoldspink9@gmail.com", passwordEncoder.encode("K8vYQF")));
        this.users.add(new User("Klaus", "Hawkridge", "khawkridge0@gmail.com", passwordEncoder.encode("W0ciC2k2b9ro")));

        return args -> {
            for (User user : users) {
                repository.save(user);
            }
        };
    }

    @Bean
    CommandLineRunner itemsSampleData(ItemRepository repository) {
        this.items.add(new Item(this.users.get(0), Date.valueOf("2018-10-10"), "asus x53u", "laptopul lucreaza numai trebue alt hdd al lui este cu baduri \n" +
                "\n" +  "caracteristici,:\n" + "ram ddr3 2Gb\n" +"procesorul amd cu doua nucleie\n" + "ecran 15.6\n" +
                "\n" + "in complecte intra laptopul si incarcator\n" +"\n" +"bateria tine 30-40 minute"));
        this.items.add(new Item(this.users.get(0), Date.valueOf("2018-10-10"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(0), Date.valueOf("2018-10-10"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(1), Date.valueOf("2018-10-12"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(1), Date.valueOf("2018-10-12"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(2), Date.valueOf("2018-10-14"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(4), Date.valueOf("2018-10-16"), "Laptop", "very nice condition"));
        this.items.add(new Item(this.users.get(7), Date.valueOf("2018-10-19"), "Laptop", "very nice condition"));

        return args -> {
            for (Item item : items) {
                repository.save(item);
            }
        };
    }

    @Bean
    CommandLineRunner imageSampleData(ImageRepository repository) {
        byte[] arrayImg1 = null;
        byte[] arrayImg2 = null;
        byte[] arrayImg3 = null;
        byte[] arrayImg4 = null;


        ClassPathResource image1 = new ClassPathResource("images/image1.jpg");
        ClassPathResource image2 = new ClassPathResource("images/image2.jpg");
        ClassPathResource image3 = new ClassPathResource("images/image3.jpg");
        ClassPathResource image4 = new ClassPathResource("images/image4.jpg");


        try {
            arrayImg1 = new byte[(int) image1.contentLength()];
            image1.getInputStream().read(arrayImg1);

            arrayImg2 = new byte[(int) image2.contentLength()];
            image2.getInputStream().read(arrayImg2);

            arrayImg3 = new byte[(int) image3.contentLength()];
            image3.getInputStream().read(arrayImg3);

            arrayImg4 = new byte[(int) image4.contentLength()];
            image4.getInputStream().read(arrayImg4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image itemImage1 = new Image(this.items.get(0), "image1.jpg", arrayImg1);
        Image itemImage2 = new Image(this.items.get(0), "image2.jpg", arrayImg2);
        Image itemImage3 = new Image(this.items.get(0), "image3.jpg", arrayImg3);
        Image itemImage4 = new Image(this.items.get(0), "image4.jpg", arrayImg4);

        return args -> {
            repository.save(itemImage1);
            repository.save(itemImage2);
            repository.save(itemImage3);
            repository.save(itemImage4);
        };
    }

}
