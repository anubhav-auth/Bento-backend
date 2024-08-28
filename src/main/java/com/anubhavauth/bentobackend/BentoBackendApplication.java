package com.anubhavauth.bentobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class BentoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BentoBackendApplication.class, args);
    }

}
