package com.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "auctions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuctionEntity {

    @Id
    private String cardId;

    private int price;
    private int amount;

}
