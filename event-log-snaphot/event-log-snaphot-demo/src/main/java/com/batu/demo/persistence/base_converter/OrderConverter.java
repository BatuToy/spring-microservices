package com.batu.demo.persistence.base_converter;

import com.batu.demo.domain.dto.OrderDto;
import com.fasterxml.jackson.core.type.TypeReference;

public class OrderConverter extends PayloadConverter<OrderDto>{
    public OrderConverter(TypeReference<OrderDto> ref) {
        super(ref);
    }
}
