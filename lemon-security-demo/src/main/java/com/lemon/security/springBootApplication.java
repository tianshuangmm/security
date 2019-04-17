package com.lemon.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class springBootApplication {
    @GetMapping("/hello")
    public String hello(){
        return "hello word!";
    }
    public static void main(String[] args) {
        SpringApplication.run(springBootApplication.class,args);
    }
}
