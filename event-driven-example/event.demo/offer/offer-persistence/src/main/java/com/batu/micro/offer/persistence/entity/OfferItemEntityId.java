package com.batu.micro.offer.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

/*
 * @created 30/07/2025 ~~ 14:12
 * author: batu
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferItemEntityId {

    private UUID offerId;
    private Long itemId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OfferItemEntityId that = (OfferItemEntityId) o;
        return Objects.equals(offerId, that.offerId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, itemId);
    }

}
