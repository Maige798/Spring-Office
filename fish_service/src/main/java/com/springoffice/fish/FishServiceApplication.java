package com.springoffice.fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.springoffice")
public class FishServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FishServiceApplication.class, args);
    }
}
