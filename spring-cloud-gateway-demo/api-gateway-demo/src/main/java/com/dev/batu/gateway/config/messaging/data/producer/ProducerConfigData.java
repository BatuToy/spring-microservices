package com.dev.batu.gateway.config.messaging.data.producer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.producer")
@Setter
@Getter
public class ProducerConfigData {
    private String keySerializer;
    private String valueSerializer;
}
