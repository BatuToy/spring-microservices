package com.batu.micro.offer.persistence.entity;

/*
 * @created 30/07/2025 ~~ 14:11
 * author: batu
 */

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "t_offer_item")
@Entity(name = "offer_item")
public class OfferItemEntity {

    @EmbeddedId
    private OfferItemEntityId offerItemId;

    @MapsId(value = "offerId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OFFER_ID", referencedColumnName = "OFFER_ID")
    private OfferEntity offer;

    @PositiveOrZero
    @Column(name = "SUB_TOTAL_PRICE")
    private BigDecimal subTotalPrice;

    @Positive
    @Column(name = "ITEM_PRICE")
    private BigDecimal itemPrice;

}
