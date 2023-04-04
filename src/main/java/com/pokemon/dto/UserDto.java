package com.pokemon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private String email;
    private int pokeCoins;
    private int points;
    private List<UserCardDto> cards;

}
