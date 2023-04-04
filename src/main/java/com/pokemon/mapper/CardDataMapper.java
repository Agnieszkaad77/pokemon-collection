package com.pokemon.mapper;

import com.pokemon.dto.CardDataDto;
import com.pokemon.entity.CardDataEntity;
import org.springframework.stereotype.Component;

@Component
public class CardDataMapper {

    public CardDataEntity toCardEntity(CardDataDto cardDataDto) {
        return CardDataEntity.builder()
                .id(cardDataDto.getId())
                .name(cardDataDto.getName())
                .largeImage(cardDataDto.getLargeImage())
                .build();
    }

    public CardDataDto toCardDto(CardDataEntity cardDataEntity) {
        return CardDataDto.builder()
                .id(cardDataEntity.getId())
                .name(cardDataEntity.getName())
                .largeImage(cardDataEntity.getLargeImage())
                .build();
    }
}
