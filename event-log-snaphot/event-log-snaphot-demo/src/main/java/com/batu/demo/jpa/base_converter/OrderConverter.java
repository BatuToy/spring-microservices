package com.batu.demo.jpa.base_converter;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.fasterxml.jackson.core.type.TypeReference;

public class OrderConverter extends PayloadConverter<OrderAggregate>{
    public OrderConverter(TypeReference<OrderAggregate> ref) {
        super(ref);
    }
}
