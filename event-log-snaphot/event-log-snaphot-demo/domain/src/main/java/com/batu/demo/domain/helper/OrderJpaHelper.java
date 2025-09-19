package com.batu.demo.domain.helper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.port.output.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class OrderJpaHelper {

    private static final Logger logger = Logger.getLogger(OrderJpaHelper.class.getSimpleName());

    private final OrderRepositoryPort orderRepositoryPort;

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderAggregate saveOrder(OrderAggregate order) {
        OrderAggregate orderAggregate = orderRepositoryPort.saveOrder(order);
        if(Objects.isNull(orderAggregate)) {
            logger.severe("");
            throw new RuntimeException("");
        }
        return orderAggregate;
    }
}
