package com.batu.demo.domain.port.input;

import com.batu.demo.domain.aggregate.OrderAggregate;

public interface OrderService {

    void updateOrder(OrderAggregate orderAggregate);

    void initializeOrder();

}
