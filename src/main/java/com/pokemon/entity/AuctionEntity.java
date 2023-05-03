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

    //TODO: refactor - set appropriate names for SQL
    @ManyToOne
    private UserCardEntity userCardEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    /**
     * This field stands for the whole auction price. It is not related to amount of cards in auction.
     */
    @Column(name = "full_price")
    private int fullPrice;
    private int amount;
}
