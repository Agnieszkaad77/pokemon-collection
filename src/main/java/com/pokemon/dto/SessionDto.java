package com.pokemon.dto;


import com.pokemon.entity.UserEntity;
import com.pokemon.exception.LoginException;
import com.pokemon.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class SessionDto {

    private Long userEntityId;
    private LocalDateTime loginDate;
    private UserRepository userRepository;
    private static String MESSAGE = "User is not logged!";

    public SessionDto(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(UserEntity userEntity) {
        this.userEntityId = userEntity.getId();
        this.loginDate = LocalDateTime.now();
    }

    public UserEntity getUserOrThrow() {
        if (userEntityId == null) {
            throw new LoginException(MESSAGE);
        }
        return userRepository.findById(userEntityId)
                .orElseThrow(() -> new LoginException(MESSAGE));
    }

    public Optional<UserEntity> getUser() {
        if (userEntityId == null) {
            return Optional.empty();
        }
        return userRepository.findById(userEntityId);
    }
}