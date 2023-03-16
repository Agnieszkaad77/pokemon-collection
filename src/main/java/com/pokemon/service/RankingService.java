package com.pokemon.service;

import com.pokemon.entity.UserEntity;
import com.pokemon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingService {

    private UserRepository userRepository;

    public List<UserEntity> getRanking() {
        return userRepository.findTop5ByAgreeTrueOrderByPointsDesc();
    }
}
