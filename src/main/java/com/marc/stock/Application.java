package com.marc.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
@Slf4j
public class Application {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.info("Spring boot application started");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}