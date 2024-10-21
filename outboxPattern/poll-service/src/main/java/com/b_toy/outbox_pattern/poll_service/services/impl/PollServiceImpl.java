package com.b_toy.outbox_pattern.poll_service.services.impl;

import com.b_toy.outbox_pattern.poll_service.config.kafka.publisher.KafkaPublisher;
import com.b_toy.outbox_pattern.poll_service.model.Outbox;
import com.b_toy.outbox_pattern.poll_service.repository.OutboxRepository;
import com.b_toy.outbox_pattern.poll_service.services.PollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.b_toy.outbox_pattern.poll_service.utils.Constants.*;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class PollServiceImpl implements PollService {

    private final OutboxRepository outboxRepository;
    private final KafkaPublisher kafkaPublisher;

    @Override
    @Scheduled(fixedRate = 30000)
    public void pollMessageFromOrderService() {
        List<Outbox> unProcessedMessages = outboxRepository.unProcessedMessages();
        // sending messages already logged in KafkaPublisher service.
        Map<String, Object> kafkaConfigs = new HashMap<>();
        kafkaConfigs.put(KafkaHeaders.TOPIC, TOPIC_NAME);
        kafkaConfigs.put(KafkaHeaders.KEY, "batuhan-toy-key");
        unProcessedMessages.forEach( payload -> {
               try {
                kafkaPublisher.sendMessage(new GenericMessage(payload.getPayload(), kafkaConfigs));
                payload.setIsProcessed(true);
                outboxRepository.save(payload);
               } catch (Exception e){
                    log.error("Message cannot be sent! \n Error:{}", e.getLocalizedMessage());
               }
            }
       );
    }
}
