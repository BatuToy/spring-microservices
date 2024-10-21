package com.b_toy.outbox_pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class OutboxPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutboxPatternApplication.class, args);
	}

}
