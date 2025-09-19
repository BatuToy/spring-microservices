package com.batu.demo.persistence.adapter;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.port.output.OrderRepositoryPort;
import com.batu.demo.domain.vo.OrderId;
import com.batu.demo.persistence.mapper.OrderDataMapper;
import com.batu.demo.persistence.repo.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderRepository;

    @Override
    public Optional<OrderAggregate> retrieveOrderById(OrderId orderId) {
        return orderRepository.findOrderById(orderId.getVal()).map(OrderDataMapper::toDomain);
    }

    @Override
    public OrderAggregate saveOrder(OrderAggregate orderAggregate) {
        return OrderDataMapper.toDomain(orderRepository.save(OrderDataMapper.toEntity(orderAggregate)));
    }

    @Override
    public Optional<List<OrderAggregate>> retrieveAllOrders() {
        return Optional.of(orderRepository.findAll().stream().map(OrderDataMapper::toDomain).toList());
    }
}
