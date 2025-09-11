package com.batu.micro.container;

/*
 * @created 23/07/2025 ~~ 10:04
 * author: batu
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.batu.micro")
@EnableCaching
@EnableAsync
public class EventDrivenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventDrivenDemoApplication.class, args);
    }
}
