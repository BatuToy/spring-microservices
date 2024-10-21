package com.b_toy.outbox_pattern.config.mapper;


import com.b_toy.outbox_pattern.model.Order;
import com.b_toy.outbox_pattern.model.Outbox;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.b_toy.outbox_pattern.model.Outbox.*;

@Component
public class OrderToOutbox {

    @SneakyThrows
    public Outbox toOutbox(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        return Outbox.builder()
                .createdTime(LocalDateTime.now())
                .isProcessed(false)
                .aggregateId(order.getId().toString())
                .payload(objectMapper.writeValueAsString(order))
                .build();
    }
}
