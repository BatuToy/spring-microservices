package com.b_toy.outbox_pattern.poll_service.config.kafka.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(GenericMessage genericMessage){
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(genericMessage);

        future.whenCompleteAsync( (message, exc) -> {
            RecordMetadata data = message.getRecordMetadata();
            ProducerRecord<String, String> record = message.getProducerRecord();
            if(exc == null){
                log.info("\n MESSAGE PRODUCED SUCCESSFULLY! \n MESSAGE:{}\n TOPIC:{}\n TIMESTAMP:{}\n OFFSET:{}",
                        record, record.topic(), data.timestamp(), data.offset());
            } else {
                log.warn("\n MESSAGE IS NOT PRODUCED! \n ERROR:{}\n MESSAGE:{}\n TOPIC:{}\n TIMESTAMP:{}\n OFFSET:{}",
                        exc.getLocalizedMessage(), record, record.topic(), data.timestamp(), data.offset());
            }
        });
    }
}
