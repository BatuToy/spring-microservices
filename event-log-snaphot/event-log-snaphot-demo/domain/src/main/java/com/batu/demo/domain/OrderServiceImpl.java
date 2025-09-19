package com.batu.demo.domain;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.helper.OrderOutboxHelper;
import com.batu.demo.domain.port.input.OrderService;
import com.batu.demo.domain.port.output.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderOutboxHelper orderOutboxHelper;

    @Override
    public void updateOrder(OrderAggregate orderAggregate) {
        orderRepositoryPort.saveOrder(orderAggregate);
    }

    public void initializeOrder() {
        OrderAggregate initializedOrder = new OrderAggregate();
        OrderAggregate persistedOrder = orderRepositoryPort.saveOrder(initializedOrder);
        orderOutboxHelper
                .saveOrderOutboxMessage(new DomainEvent<>(
                        persistedOrder.getOrderId().getVal().toString(),
                        persistedOrder
                ));
    }
}
