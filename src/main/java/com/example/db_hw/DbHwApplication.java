package com.example.db_hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.ls.LSOutput;

@SpringBootApplication
public class DbHwApplication {
public static String ip;
public static String password;
public static String name;


    public static void main(String[] args) {
        System.out.println(args[0]);
        ip = args[0];
        password = args[1];
        SpringApplication.run(DbHwApplication.class, args);

    }

}
