package com.dev.batu.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dev.batu.demo.api")
public class DummyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyApplication.class);
    }
}
