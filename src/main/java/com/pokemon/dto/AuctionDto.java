package com.pokemon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

    private long id;

    private long userCardId;
    private int fullPrice;
    private int amount;

    //for auctions view scenario
    private String name;
    private String largeImage;
    private boolean yours;
}
