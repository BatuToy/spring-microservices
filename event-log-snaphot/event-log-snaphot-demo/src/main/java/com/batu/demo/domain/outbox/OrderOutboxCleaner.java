package com.batu.demo.domain.outbox;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.outbox.helper.OrderOutboxHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class OrderOutboxCleaner {

    private static final Logger logger = Logger.getLogger(OrderOutboxScheduler.class.getSimpleName());

    private final OrderOutboxHelper orderOutboxHelper;

    @Scheduled(cron = "@midnight")
    void cleanCompletedAndFailedOrderOutboxMessages() {
        List<DomainEvent<OrderAggregate>> completedOutboxes = orderOutboxHelper.retrieveFinishedOrderOutboxMessages();
        List<DomainEvent<OrderAggregate>> failedOutboxes = orderOutboxHelper.retrieveFailedOrderOutboxMessages();
        completedOutboxes.addAll(failedOutboxes);
        List<DomainEvent<OrderAggregate>> cleanOutboxMessages = completedOutboxes;
        logger.info("Order Outbox Messages= " + cleanOutboxMessages.size() +" cleaning!");
        orderOutboxHelper.deleteAllOutboxMessages(cleanOutboxMessages);
        logger.severe("Order Outbox Messages= " + cleanOutboxMessages.size() +" cleaning!");
    }
}
