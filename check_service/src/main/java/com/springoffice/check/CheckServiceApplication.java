package com.springoffice.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springoffice")
@EnableFeignClients
public class CheckServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckServiceApplication.class, args);
    }
}
