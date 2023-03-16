package com.pokemon.mapper;

import com.pokemon.dto.CardDto;
import com.pokemon.entity.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardEntity toCardEntity(CardDto cardDto) {
        return CardEntity.builder()
                .id(cardDto.getId())
                .name(cardDto.getName())
                .largeImage(cardDto.getLargeImage())
                .build();
    }

    public CardDto toCardDto(CardEntity cardEntity) {
        return CardDto.builder()
                .id(cardEntity.getId())
                .name(cardEntity.getName())
                .largeImage(cardEntity.getLargeImage())
                .build();
    }
}
