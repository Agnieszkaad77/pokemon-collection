package com.pokemon.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private UserCardEntity userCardEntity;

    private int price;
    private int amount;

}
