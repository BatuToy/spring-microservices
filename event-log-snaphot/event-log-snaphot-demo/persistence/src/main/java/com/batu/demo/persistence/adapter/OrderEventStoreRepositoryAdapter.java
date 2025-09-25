package com.batu.demo.persistence.adapter;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.port.output.OrderEventStoreRepositoryPort;
import com.batu.demo.domain.vo.OrderId;
import com.batu.demo.persistence.mapper.EventStoreDataMapper;
import com.batu.demo.persistence.repo.OrderEventStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderEventStoreRepositoryAdapter implements OrderEventStoreRepositoryPort<OrderAggregate> {

    private final OrderEventStoreRepository orderEventStoreRepository;


    @Override
    public DomainEvent<OrderAggregate> saveOrderEventToEventStore(DomainEvent<OrderAggregate> orderDomainEvent) {
        return EventStoreDataMapper
                .toOrderDomainEvent(orderEventStoreRepository.save(EventStoreDataMapper.toEventStore(orderDomainEvent)));
    }

    @Override
    public List<DomainEvent<OrderAggregate>> retrieveAllEventsOfOrderByAggregateId(OrderId orderId) {
        return orderEventStoreRepository.findByAggregateId(orderId.getVal().toString())
                .stream().flatMap(List::stream).map(EventStoreDataMapper::toOrderDomainEvent).toList();
    }


}
