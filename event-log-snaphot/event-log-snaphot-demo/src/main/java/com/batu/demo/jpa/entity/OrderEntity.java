package com.batu.demo.jpa.entity;


import com.batu.demo.domain.vo.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "order")
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    private UUID id;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<OrderLineItemEntity> items;

    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    @Column(name = "ORDER_STATUS")
    private Status status;
}
