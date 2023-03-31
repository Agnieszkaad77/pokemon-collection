package com.pokemon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

    private long userCardId;
    private int price;
    private int amount;

}
