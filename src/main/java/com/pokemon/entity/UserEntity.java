package com.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode
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

    public void addCards(Set<CardDataEntity> purchasedCards) {
        for (CardDataEntity purchasedCard : purchasedCards) {
            addCards(purchasedCard, 1);
        }
        this.points += purchasedCards.size();
    }

    public void addCards(CardDataEntity cardDataEntity, int amount) {
        Optional<UserCardEntity> userCardOptional = findUserCard(cardDataEntity);
        if (userCardOptional.isEmpty()) {
            cards.add(new UserCardEntity(cardDataEntity, this,amount));
        } else {
            userCardOptional.get().increaseOwnedAmount(amount);
        }
    }

    private Optional<UserCardEntity> findUserCard(CardDataEntity cardDataEntity) {
        return cards.stream()
                .filter(userCard -> userCard.getCardDataEntity().equals(cardDataEntity))
                .findFirst();
    }
}
