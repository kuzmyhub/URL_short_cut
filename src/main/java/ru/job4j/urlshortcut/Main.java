package ru.job4j.urlshortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("http://localhost:8080/site/sign-up");
        System.out.println("http://localhost:8080/login");
        System.out.println("http://localhost:8080/link/convert");
        System.out.println("http://localhost:8080/link/redirect/{CODE}");
        System.out.println("http://localhost:8080/link/statistic");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
