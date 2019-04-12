package com.example.emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmtApplication.class, args);
    }

}
