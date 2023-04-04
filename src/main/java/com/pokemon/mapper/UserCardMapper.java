package com.pokemon.mapper;

import com.pokemon.dto.UserCardDto;
import com.pokemon.entity.UserCardEntity;
import org.springframework.stereotype.Component;

@Component
public class UserCardMapper {

    public UserCardDto toUserCardDto(UserCardEntity userCardEntity) {
        return UserCardDto.builder()
                .id(userCardEntity.getId())
                .ownedAmount(userCardEntity.getOwnedAmount())
                .amountInAuctions(userCardEntity.getAmountInAuctions())
                .cardDataId(userCardEntity.getCardDataEntity().getId())
                .name(userCardEntity.getCardDataEntity().getName())
                .largeImage(userCardEntity.getCardDataEntity().getLargeImage())
                .build();
    }
}
