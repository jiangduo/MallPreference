package com.totoro.mall.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.totoro.mall"})
public class ManagerApplication {
    public static void main(String[] args){
        SpringApplication.run(ManagerApplication.class, args);
    }

}
