package com.dev.batu.gateway.config.messaging.data.consumer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.consumer")
@Getter
@Setter
public class ConsumerConfigData {
    private String keyDeserializer;
    private String valueDeserializer;
}
