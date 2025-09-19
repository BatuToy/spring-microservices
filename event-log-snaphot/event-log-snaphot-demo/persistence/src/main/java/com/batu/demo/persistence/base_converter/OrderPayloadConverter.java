package com.batu.demo.persistence.base_converter;

import com.batu.demo.domain.dto.OrderDto;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderPayloadConverter extends PayloadConverter<OrderDto>{
    public OrderPayloadConverter(TypeReference<OrderDto> ref) {
        super(ref);
    }
}
