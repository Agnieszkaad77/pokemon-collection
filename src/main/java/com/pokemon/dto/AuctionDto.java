package com.pokemon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

    private String cardId;
    private int price;
    private int amount;

}
