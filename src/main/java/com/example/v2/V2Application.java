package com.example.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class V2Application {

    public static void main(String[] args) {
        SpringApplication.run(V2Application.class, args);
    }

}
