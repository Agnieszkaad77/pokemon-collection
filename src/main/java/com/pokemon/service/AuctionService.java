package com.pokemon.service;
import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.entity.UserCardEntity;
import com.pokemon.entity.UserEntity;
import com.pokemon.exception.AuctionException;
import com.pokemon.mapper.AuctionMapper;
import com.pokemon.repository.AuctionRepository;
import com.pokemon.repository.UserCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;
    private UserCardRepository userCardRepository;
    private LoginService loginService;

    public void saveAuction(AuctionDto auctionDto) {
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);

        UserCardEntity userCardEntity = userCardRepository.findById(auctionDto.getUserCardId())
                .orElseThrow(() -> new AuctionException("No card found!"));

        if (auctionDto.getAmount() > userCardEntity.getAmountNotInAuctions()) {
            throw new AuctionException("Not enough cards of this type in collection!");
        }

        if (!checkAmountCorrectness(auctionDto)) {
            throw new AuctionException("Incorrect amount selected!");
        }

        if (!checkPriceCorrectness(auctionDto)) {
            throw new AuctionException("Incorrect price selected");
        }

        userCardEntity.increaseAmountInAuctions(auctionDto.getAmount());
        auctionRepository.save(auctionEntity);
    }

    private boolean checkAmountCorrectness(AuctionDto auctionDto) {
        return auctionDto.getAmount() > 0;
    }

    private boolean checkPriceCorrectness(AuctionDto auctionDto) {
        return auctionDto.getPrice() >= 0;
    }

    public List<AuctionDto> getAllAuctions() {
        return auctionRepository.findAll().stream()
                .map(auctionEntity -> auctionMapper.toAuctionDto(auctionEntity))
                .toList();
    }

    public void purchaseAuction(long id) {
        AuctionEntity auctionEntity = auctionRepository.findById(id)
                .orElseThrow(() -> new AuctionException("Auction does not exist!"));
        UserEntity buyer = loginService.getLoggedUserEntity();
        UserEntity seller = auctionEntity.getUserEntity();
        buyer.addCards(auctionEntity.getUserCardEntity().getCardDataEntity(),auctionEntity.getAmount());
    }
}
