package com.pokemon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCardDto {

    private long id;

    private int ownedAmount;
    private int amountInAuctions;
    private String cardDataId;
    private String name;
    private String largeImage;

}
