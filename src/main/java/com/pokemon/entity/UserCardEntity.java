package com.pokemon.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "user_cards")
@Getter
@EqualsAndHashCode
public class UserCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int ownedAmount = 1;
    private int amountInAuctions = 0;

    @ManyToOne
    private CardDataEntity cardDataEntity;

    @ManyToOne
    private UserEntity userEntity;


    protected UserCardEntity() {

    }

    public UserCardEntity(CardDataEntity cardDataEntity, UserEntity userEntity) {
        this.cardDataEntity = cardDataEntity;
        this.userEntity = userEntity;
    }

    public int getAmountNotInAuctions() {
        return ownedAmount - amountInAuctions;
    }

    public void increaseAmountInAuctions(int amount) {
        amountInAuctions += amount;
    }
}
