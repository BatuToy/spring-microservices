package com.batu.demo.domain.outbox;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.domain.helper.OrderOutboxHelper;
import com.batu.demo.domain.vo.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component("outbox_scheduler")
@RequiredArgsConstructor
public class OrderOutboxScheduler {

    private static final Logger logger = Logger.getLogger(OrderOutboxScheduler.class.getSimpleName());

    private final OrderOutboxHelper orderOutboxHelper;

    @Scheduled(cron = "59 * * * * *")
    void sendOrdersToRelevantTopics() {
        List<DomainEvent<OrderAggregate>> domainEvents = orderOutboxHelper.retrieveStartedOrderOutboxMessages();
        //domainEvents.forEach(event -> kafkaPublisher.publish(event, updateOutboxStatus(event.getId(), updateOutboxStatus)));
        logger.info("Order Outbox Messages= " + domainEvents.size() + " sent to Related Topic !");
    }

    public void updateOutboxStatus(EventId eventId, OutboxStatus status) {
        orderOutboxHelper.updateStatusOfOrderOutboxMessage(eventId, status);
    }

}
