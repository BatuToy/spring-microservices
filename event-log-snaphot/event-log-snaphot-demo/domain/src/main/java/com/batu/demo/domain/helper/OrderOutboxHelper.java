package com.batu.demo.domain.helper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.domain.port.output.OutboxJpaPort;
import com.batu.demo.domain.vo.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class OrderOutboxHelper {

    private static final Logger logger = Logger.getLogger(OrderOutboxHelper.class.getSimpleName());

    private final OutboxJpaPort<OrderAggregate> orderOutbox;

    @Transactional(readOnly = true)
    public List<DomainEvent<OrderAggregate>> retrieveStartedOrderOutboxMessages() {
        Optional<List<DomainEvent<OrderAggregate>>> optOrderOutboxes = orderOutbox.retrieveEventsByOutboxStatus(OutboxStatus.STARTED);
        if (optOrderOutboxes.isEmpty()) {
            logger.severe("An error occurred while retrieving the Order Outbox Messages (Started) from persist store !");
            throw new RuntimeException("An error occurred while retrieving the Order Outbox Messages (Started) from persist store !");
        }
        return optOrderOutboxes.get();
    }

    @Transactional(readOnly = true)
    public List<DomainEvent<OrderAggregate>> retrieveCompletedOutboxMessages() {
        Optional<List<DomainEvent<OrderAggregate>>> optOrderOutboxes = orderOutbox.retrieveEventsByOutboxStatus(OutboxStatus.COMPLETED);
        if (optOrderOutboxes.isEmpty()) {
            logger.severe("An error occurred while retrieving the Order Outbox Messages (Finished) from persist store !");
            throw new RuntimeException("An error occurred while retrieving the Order Outbox Messages (Finished) from persist store !");
        }
        return optOrderOutboxes.get();
    }

    @Transactional(readOnly = true)
    public List<DomainEvent<OrderAggregate>> retrieveFailedOrderOutboxMessages() {
        Optional<List<DomainEvent<OrderAggregate>>> optOrderOutboxes = orderOutbox.retrieveEventsByOutboxStatus(OutboxStatus.FAILED);
        if (optOrderOutboxes.isEmpty()) {
            logger.severe("An error occurred while retrieving the Order Outbox Messages (Failed) from persist store !");
            throw new RuntimeException("An error occurred while retrieving the Order Outbox Messages (Failed) from persist store !");
        }
        return optOrderOutboxes.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStatusOfOrderOutboxMessage(EventId eventId, OutboxStatus status) {
        orderOutbox.updateOutboxStatusOfEvent(eventId, status);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllOutboxMessages(List<DomainEvent<OrderAggregate>> events) {
        orderOutbox.deleteAllOutboxMessages(events);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrderOutboxMessage(DomainEvent<OrderAggregate> event) {
        orderOutbox.saveOutboxEvent(event);
    }
}
