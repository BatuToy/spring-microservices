package com.b_toy.outbox_pattern.poll_service.config.kafka.consumer;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.b_toy.outbox_pattern.poll_service.utils.Constants.GROUP_ID;
import static com.b_toy.outbox_pattern.poll_service.utils.Constants.TOPIC_NAME;
@Slf4j
@Service
public class KafkaConsumerService {

    // This method should be in other services to listen this kafka broker and get all unProcessed Messages from Order-Service.
    @KafkaListener( topics = TOPIC_NAME,
    groupId = GROUP_ID)
    public void consumeTheProcessedMessagesFromPollService(String payload){
      if(StringUtil.isNullOrEmpty(payload)){
          log.error("Payload comes null! PAYLOAD:{}", payload);
      } else {
          log.info("PAYMENT AND INVENTORY SERVICE CONSUMED THE PAYLOAD SUCCESSFULLY WITH TRANSACTIONAL OUTBOX PATTERN\n" +
                  "PAYLOAD:{}", payload);
      }

    }
}
