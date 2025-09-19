package com.batu.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan(basePackages = "com.batu.demo.persistence.entity")
@EnableJpaRepositories("com.batu.demo.persistence.repo")
@SpringBootApplication(scanBasePackages = "com.batu.demo")
public class EventLogSnapshotDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventLogSnapshotDemoApplication.class, args);
	}

}
