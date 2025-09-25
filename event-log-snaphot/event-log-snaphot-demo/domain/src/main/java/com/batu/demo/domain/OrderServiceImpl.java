package com.batu.demo.domain;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.helper.OrderOutboxHelper;
import com.batu.demo.domain.port.input.OrderService;
import com.batu.demo.domain.port.output.OrderEventStoreRepositoryPort;
import com.batu.demo.domain.port.output.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderEventStoreRepositoryPort<OrderAggregate> orderAggregateOrderEventStoreRepositoryPort;
    private final OrderOutboxHelper orderOutboxHelper;

    public void initializeOrder() {
        // TODO= In case of stepping more then one version in one flow this logic is not going to work !
        OrderAggregate initializedOrder = new OrderAggregate();
        OrderAggregate persistedOrder = orderRepositoryPort.saveOrder(initializedOrder);
        DomainEvent<OrderAggregate> currentEvent = initializedOrder.getChange().getChangeMap().values().stream().filter(orderDomainEvent ->
                orderDomainEvent.getVersion().equals(initializedOrder.getChange().getCurrentVersionNumber()))
                .findFirst()
                .orElseThrow();
        orderAggregateOrderEventStoreRepositoryPort.saveOrderEventToEventStore(currentEvent);
        orderOutboxHelper
                .saveOrderOutboxMessage(new DomainEvent<>(
                        persistedOrder.getOrderId().getVal().toString(),
                        persistedOrder
                ));
    }
}
