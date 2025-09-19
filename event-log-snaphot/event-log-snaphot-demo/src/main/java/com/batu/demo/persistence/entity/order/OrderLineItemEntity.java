package com.batu.demo.persistence.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;

@Entity(name = "line_item")
@Table(name = "t_line_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderLineItemEntity {

    @EmbeddedId
    @Column(name = "LINE_ITEM_ID")
    private OrderLineItemId id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private OrderEntity order;

    @Column(name = "SUB_TOTAL")
    private BigDecimal subTotalPrice;

    @Column(name = "ITEM_PRICE")
    private BigDecimal itemPrice;

    @Column(name = "QUANTITY")
    private Integer quantity;

}
