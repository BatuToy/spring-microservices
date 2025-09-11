package com.batu.micro.offer.persistence.entity;

/*
 * @created 30/07/2025 ~~ 12:45
 * author: batu
 */

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "offer")
@Table(name = "t_offer", indexes = {@Index(name = "idx_offer_offer_code", columnList = "OFFER_CODE")})
public class OfferEntity {

    @Id
    @Column(name = "OFFER_ID")
    private UUID id;

    @Column(name = "OFFER_CODE")
    private String offerCode;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OfferItemEntity> offerItems;

}
