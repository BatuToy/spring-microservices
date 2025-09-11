package com.dev.batu.gateway.config.messaging.data.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.common")
@Getter
@Setter
public class KafkaCommonDataConfig {
    private String bootStrapServer;
}
