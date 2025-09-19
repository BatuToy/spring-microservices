package com.batu.demo.persistence.entity.outbox;

import com.batu.demo.domain.dto.OrderDto;
import com.batu.demo.persistence.base_converter.OrderPayloadConverter;
import com.batu.demo.persistence.entity.base.AbstractOutboxEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "order_event")
@Table(name = "t_order_outbox")
@SuperBuilder
@Getter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderOutboxEntity extends AbstractOutboxEntity {

    @Convert(converter = OrderPayloadConverter.class)
    @Column(name = "PAYLOAD", columnDefinition = "json")
    private OrderDto payload;

}
