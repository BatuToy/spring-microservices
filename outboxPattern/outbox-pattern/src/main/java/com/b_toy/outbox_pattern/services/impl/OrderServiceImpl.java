package com.b_toy.outbox_pattern.services.impl;

import com.b_toy.outbox_pattern.config.mapper.Mapper;
import com.b_toy.outbox_pattern.config.mapper.OrderToOutbox;
import com.b_toy.outbox_pattern.dto.OrderRequestDto;
import com.b_toy.outbox_pattern.dto.OrderResponseDto;
import com.b_toy.outbox_pattern.model.Order;
import com.b_toy.outbox_pattern.model.Outbox;
import com.b_toy.outbox_pattern.repository.OrderRepository;
import com.b_toy.outbox_pattern.repository.OutboxRepository;
import com.b_toy.outbox_pattern.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final Mapper mapper;
    
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = mapper.map(orderRequestDto, Order.class);
        // ToDo:  Why we cannot create a dates in the order entity?
        orderRepository.save(order);
        //Outbox outbox = orderToOutbox.toOutbox(order);
        Outbox outbox = mapper.map(order, Outbox.class);
        outboxRepository.save(outbox);
        log.info("REQUEST: {} \n ORDER: {} \n OUTBOX: {}",
                orderRequestDto,
                order,
                outbox
                );
        return mapper.map(order, OrderResponseDto.class);
    }
}
