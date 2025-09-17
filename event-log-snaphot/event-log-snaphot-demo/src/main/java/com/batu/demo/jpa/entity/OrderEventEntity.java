package com.batu.demo.jpa.entity;

import com.batu.demo.dto.OrderDto;
import com.batu.demo.jpa.base_converter.OrderConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "order_event")
@Table(name = "t_order_event")
@SuperBuilder
@Getter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderEventEntity extends EventEntity {

    @Convert(converter = OrderConverter.class)
    @Column(name = "PAYLOAD", columnDefinition = "json")
    private OrderDto payload;

}
