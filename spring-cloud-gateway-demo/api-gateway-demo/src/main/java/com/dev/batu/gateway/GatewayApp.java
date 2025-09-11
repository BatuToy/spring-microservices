package com.dev.batu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dev.batu.gateway")
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class);
    }

}
