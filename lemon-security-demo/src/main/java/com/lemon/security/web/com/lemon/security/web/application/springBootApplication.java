package com.lemon.security.web.com.lemon.security.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class springBootApplication {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello word!";
    }
    public static void main(String[] args) {
        SpringApplication.run(springBootApplication.class,args);
    }
}
