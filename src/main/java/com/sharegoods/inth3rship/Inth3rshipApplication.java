package com.sharegoods.inth3rship;

import com.sharegoods.inth3rship.common.CreateTables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Inth3rshipApplication
{
    public static void main(String[] args)
    {

        CreateTables.GenerateTables();

        SpringApplication.run(Inth3rshipApplication.class, args);
    }
}