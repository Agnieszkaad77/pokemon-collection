package com.pokemon.service;
import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.entity.CardEntity;
import com.pokemon.entity.UserEntity;
import com.pokemon.exception.AuctionException;
import com.pokemon.mapper.AuctionMapper;
import com.pokemon.repository.AuctionRepository;
import com.pokemon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;
    private LoginService loginService;
    private UserRepository userRepository;

    public void saveAuction(AuctionDto auctionDto) {
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);
        UserEntity loggedUser = loginService.getLoggedUserEntity();
        List<CardEntity> cards = loggedUser.getCards();

        if (!checkSameCardsAmount(auctionDto, cards)) {
            throw new AuctionException("Not enough cards of this type in collection!");
        }

        if (!checkAmountCorrectness(auctionDto)) {
            throw new AuctionException("Incorrect amount selected!");
        }

        if (!checkPriceCorrectness(auctionDto)) {
            throw new AuctionException("Incorrect price selected");
        }

        updateUser(auctionDto, loggedUser, cards);
        auctionRepository.save(auctionEntity);
    }

    private boolean checkSameCardsAmount(AuctionDto auctionDto, List<CardEntity> cards) {
        int sameCardsAmount = 0;
        for (CardEntity cardToSell : cards) {
            if (cardToSell.getId().equals(auctionDto.getCardId())) {
                sameCardsAmount++;
            }
        }
        return auctionDto.getAmount() <= sameCardsAmount;
    }

    private boolean checkAmountCorrectness(AuctionDto auctionDto) {
        return auctionDto.getAmount() > 0;
    }

    private boolean checkPriceCorrectness(AuctionDto auctionDto) {
        return auctionDto.getPrice() >= 0;
    }

    private void updateUser(AuctionDto auctionDto, UserEntity loggedUser, List<CardEntity> cards) {
        cards.removeIf(cardToSell -> cardToSell.getId().equals(auctionDto.getCardId()));
        loggedUser.increasePokeCoins(auctionDto.getPrice());
        loggedUser.setPoints(cards.size());
        userRepository.save(loggedUser);
    }
}
