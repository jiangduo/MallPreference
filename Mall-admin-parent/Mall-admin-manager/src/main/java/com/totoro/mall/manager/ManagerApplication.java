package com.totoro.mall.manager;

import com.totoro.mall.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.totoro.mall"})
@EnableConfigurationProperties(value = {UserProperties.class})//注解则告诉 Spring Boot 启用对这个配置类的支持，
// 以便在应用程序中使用它。使用这种方式，你可以在其他组件中注入 UserProperties 类，
public class ManagerApplication {
    public static void main(String[] args){
        SpringApplication.run(ManagerApplication.class, args);
    }

}
