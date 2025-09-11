package com.dev.batu.gateway.config.messaging;

import com.dev.batu.gateway.config.messaging.data.common.KafkaCommonDataConfig;
import com.dev.batu.gateway.config.messaging.data.consumer.ConsumerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig<K extends Serializable, V> {

    private final ConsumerConfigData consumerConfigData;
    private final KafkaCommonDataConfig kafkaCommonDataConfig;

    public Map<String, Object> config(){
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaCommonDataConfig.getBootStrapServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumerConfigData.getValueDeserializer());
        return props;
    }

    @Bean
    public DefaultKafkaConsumerFactory<K, V> defaultFactory(){
        return new DefaultKafkaConsumerFactory<>(config());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<K, V> concurrentKafkaListenerContainer(){
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
