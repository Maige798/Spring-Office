package com.springoffice.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springoffice")
public class RoleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleServiceApplication.class, args);
    }
}
