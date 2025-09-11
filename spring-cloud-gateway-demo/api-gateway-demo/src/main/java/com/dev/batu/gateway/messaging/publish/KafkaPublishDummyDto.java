package com.dev.batu.gateway.messaging.publish;

import com.dev.batu.gateway.config.messaging.publish.KafkaPublish;
import com.dev.batu.gateway.dto.DummyDto;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaPublishDummyDto {

    private final KafkaPublish<String, DummyDto> kafkaPublish;

    public KafkaPublishDummyDto(KafkaPublish<String, DummyDto> kafkaPublish) {
        this.kafkaPublish = kafkaPublish;
    }
    // Todo: How to parameterize this method?
    public void publish(
            @Header List<DummyDto>
    ){
        final String key = UUID.randomUUID().toString();
        kafkaPublish.send();
    }
}
