package com.pokemon.dto;


import com.pokemon.entity.UserEntity;
import com.pokemon.exception.LoginException;
import com.pokemon.repository.UserRepository;

import java.time.LocalDateTime;

public class SessionDto {

    private Long userEntityId;
    private LocalDateTime loginDate;
    private UserRepository userRepository;

    public SessionDto(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(UserEntity userEntity) {
        this.userEntityId = userEntity.getId();
        this.loginDate = LocalDateTime.now();
    }

    public UserEntity getUserOrThrow() {
        return userRepository.findById(userEntityId).orElseThrow(() -> new LoginException("User is not logged!"));
    }
}