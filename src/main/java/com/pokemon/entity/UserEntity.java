package com.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private boolean agree;
    @Builder.Default
    private int pokeCoins = 20;
    private int points;
    @Builder.Default
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<UserCardEntity> cards = new ArrayList<>();

    public void decreasePokeCoins(int price) {
        this.pokeCoins -= price;
    }

    public void increasePokeCoins(int income) {
        this.pokeCoins += income;
    }

    public void addCards(List<CardDataEntity> purchasedCards) {
        List<UserCardEntity> cards = purchasedCards.stream()
                .map(cardDataEntity -> new UserCardEntity(cardDataEntity, this))
                .toList();
        this.cards.addAll(cards);
        this.points += purchasedCards.size();
    }
}
