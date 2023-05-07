package com.example.pawnshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PawnShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawnShopApplication.class, args);
    }

}
