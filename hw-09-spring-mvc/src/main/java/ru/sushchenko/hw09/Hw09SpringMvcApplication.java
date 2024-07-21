package ru.sushchenko.hw09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw09SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw09SpringMvcApplication.class, args);
        System.out.printf("To view program visit: %n%s%n",
                "http://localhost:8080/");
    }

}
