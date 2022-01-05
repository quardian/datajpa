package com.inho.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

// Springboot 에서는 아래 설정 안해도, Application 하위에서 알아서 찾아줌.
@EnableJpaRepositories(basePackages = {"com.inho.datajpa.repository"})
public class DatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatajpaApplication.class, args);
    }

}
