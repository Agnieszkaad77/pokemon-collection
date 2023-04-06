package com.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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

    public void addCards(Set<CardDataEntity> purchasedCards) {
        List<UserCardEntity> newUserCards = new ArrayList<>();
        for (CardDataEntity purchasedCard : purchasedCards) {
            Optional<UserCardEntity> userCardOptional = findUserCard(purchasedCard);
            if (userCardOptional.isEmpty()) {
                newUserCards.add(new UserCardEntity(purchasedCard, this));
            } else {
                userCardOptional.get().increaseOwnedAmount(1);
            }
        }
        this.cards.addAll(newUserCards);
        this.points += purchasedCards.size();
    }

    private Optional<UserCardEntity> findUserCard(CardDataEntity cardDataEntity) {
        return cards.stream()
                .filter(userCard -> userCard.getCardDataEntity().equals(cardDataEntity))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
