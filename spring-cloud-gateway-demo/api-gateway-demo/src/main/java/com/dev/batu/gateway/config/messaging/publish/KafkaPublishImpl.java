package com.dev.batu.gateway.config.messaging.publish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPublishImpl<K extends Serializable, V> implements KafkaPublish<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(K key, V message, String topic, Integer partition) {
        CompletableFuture<SendResult<K, V>> sendResultCompletableFuture = kafkaTemplate.send(topic, partition, key, message);
        sendResultCompletableFuture.whenCompleteAsync( (res, err) -> {
            RecordMetadata data = res.getRecordMetadata();
            ProducerRecord<K, V> record = res.getProducerRecord();
            if(err == null){
                log.info("Key={}, Topic={}, Message={}",
                        record.key(),
                        data.topic(),
                        record.value());
            } else {
                log.error("Message can not be sent with topic= {}, key={} and value= {}",
                        data.topic(),
                        record.key(),
                        record.value());
            }
        });
    }
}
