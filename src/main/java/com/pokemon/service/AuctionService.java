package com.pokemon.service;
import com.pokemon.dto.AuctionDto;
import com.pokemon.entity.AuctionEntity;
import com.pokemon.mapper.AuctionMapper;
import com.pokemon.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;

    public void saveAuction(AuctionDto auctionDto) {
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);
        auctionRepository.save(auctionEntity);
    }
}
