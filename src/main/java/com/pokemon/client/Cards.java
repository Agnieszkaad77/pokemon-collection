package com.pokemon.client;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Cards {

    private List<JsonCard> data;
}
