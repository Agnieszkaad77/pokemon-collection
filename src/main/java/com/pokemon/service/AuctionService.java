package com.pokemon.service;

import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.entity.UserCardEntity;
import com.pokemon.entity.UserEntity;
import com.pokemon.exception.AuctionException;
import com.pokemon.mapper.AuctionMapper;
import com.pokemon.repository.AuctionRepository;
import com.pokemon.repository.UserCardRepository;
import com.pokemon.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;
    private UserCardRepository userCardRepository;
    private LoginService loginService;
    private UserRepository userRepository;

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
        return auctionDto.getFullPrice() >= 0;
    }

    public List<AuctionDto> getAllAuctions() {
        return auctionRepository.findAll().stream()
                .map(auctionEntity -> auctionMapper.toAuctionDto(auctionEntity))
                .toList();
    }

    /**
     * While purchasing auction user always buys all cards and then the auction is always closed.
     * @param id Auction's id.
     */
    @Transactional
    public void purchaseAuction(long id) {
        AuctionEntity auctionEntity = auctionRepository.findById(id)
                .orElseThrow(() -> new AuctionException("Auction does not exist!"));
        UserEntity buyer = loginService.getLoggedUserEntity();
        if (buyer.getPokeCoins() < auctionEntity.getFullPrice()) {
            throw new AuctionException("Not enough PokeCoins!");
        }
        UserEntity seller = auctionEntity.getUserEntity();
        buyer.addCards(auctionEntity.getUserCardEntity().getCardDataEntity(), auctionEntity.getAmount());
        Optional<UserCardEntity> userCardEntityRemoved =
                seller.removeCards(auctionEntity.getUserCardEntity().getCardDataEntity(), auctionEntity.getAmount());
        buyer.decreasePokeCoins(auctionEntity.getFullPrice());
        seller.increasePokeCoins(auctionEntity.getFullPrice());
        userRepository.save(buyer);
        userRepository.save(seller);
        auctionRepository.delete(auctionEntity);
        userCardEntityRemoved.ifPresent(userCard -> {
            if (userCard.getOwnedAmount() <= 0) {
                userCardRepository.delete(userCard);
            }
        });
    }
}
