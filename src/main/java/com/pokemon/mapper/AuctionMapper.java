package com.pokemon.mapper;

import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.exception.AuctionException;
import com.pokemon.repository.UserCardRepository;
import com.pokemon.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuctionMapper {

    private UserCardRepository userCardRepository;
    private LoginService loginService;

    public AuctionEntity toAuctionEntity(AuctionDto auctionDto) {
        return AuctionEntity.builder()
                .userCardEntity(userCardRepository.findById(auctionDto.getUserCardId())
                        .orElseThrow(() -> new AuctionException("No card found!")))
                .price(auctionDto.getPrice())
                .amount(auctionDto.getAmount())
                .userEntity(loginService.getLoggedUserEntity())
                .build();
    }

    public AuctionDto toAuctionDto(AuctionEntity auctionEntity) {
        return AuctionDto.builder()
                .id(auctionEntity.getId())
                .userCardId(auctionEntity.getUserCardEntity().getId())
                .price(auctionEntity.getPrice())
                .amount(auctionEntity.getAmount())
                .name(auctionEntity.getUserCardEntity().getCardDataEntity().getName())
                .largeImage(auctionEntity.getUserCardEntity().getCardDataEntity().getLargeImage())
                .build();
    }
}
