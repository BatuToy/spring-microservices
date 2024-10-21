package com.b_toy.outbox_pattern.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "NAME")
    private String orderName;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @CreatedDate
    @Column(name = "CREATE_DATE")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private LocalDateTime lastModifiedDate;

}
