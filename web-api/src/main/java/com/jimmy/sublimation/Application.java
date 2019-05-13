package com.jimmy.sublimation;

import com.jimmy.sublimation.validate.EnableValidate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableValidate
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
