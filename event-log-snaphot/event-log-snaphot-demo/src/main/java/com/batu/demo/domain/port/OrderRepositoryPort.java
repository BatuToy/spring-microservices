package com.batu.demo.domain.port;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.vo.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Optional<OrderAggregate> retrieveOrderById(OrderId orderId);

    OrderAggregate saveOrder(OrderAggregate orderAggregate);

    Optional<List<OrderAggregate>> retrieveAllOrders();

}
