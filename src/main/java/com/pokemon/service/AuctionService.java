package com.pokemon.service;
import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.entity.UserCardEntity;
import com.pokemon.exception.AuctionException;
import com.pokemon.mapper.AuctionMapper;
import com.pokemon.repository.AuctionRepository;
import com.pokemon.repository.UserCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;
    private UserCardRepository userCardRepository;

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
}
