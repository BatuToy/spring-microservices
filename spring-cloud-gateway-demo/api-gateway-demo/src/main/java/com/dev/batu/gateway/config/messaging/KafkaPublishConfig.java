package com.dev.batu.gateway.config.messaging;

import com.dev.batu.gateway.config.messaging.data.common.KafkaCommonDataConfig;
import com.dev.batu.gateway.config.messaging.data.producer.ProducerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaPublishConfig<K extends Serializable, V> {

    private final ProducerConfigData prodData;
    private final KafkaCommonDataConfig kafkaCommonDataConfig;

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate(){
        return new KafkaTemplate<>(factory());
    }

    public Map<String, Object> config(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaCommonDataConfig.getBootStrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, prodData.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, prodData.getValueSerializer());
        return props;
    }

    @Bean
    public DefaultKafkaProducerFactory<K, V> factory(){
        return new DefaultKafkaProducerFactory<>(config());
    }
}
