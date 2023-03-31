package com.pokemon.service;

import com.pokemon.dto.UserRegistrationDto;
import com.pokemon.entity.UserEntity;
import com.pokemon.exception.RegistrationException;
import com.pokemon.mapper.UserMapper;
import com.pokemon.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @PostConstruct
    public void addDummyUser() {
        if (userRepository.count() > 0) {
            return;
        }
        userRepository.save(UserEntity.builder()
                .email("jan.kowalski@gmail.com")
                .password("pass")
                .agree(true)
                .build());
    }

    public void register(UserRegistrationDto userRegistrationDto) {
        if (checkMandatoryFields(userRegistrationDto)) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())
                || !checkPasswordCorrectness(userRegistrationDto)) {
            throw new RegistrationException("Email or password is not correct!");
        }
        UserEntity userEntity = userMapper.toUserEntity(userRegistrationDto);
        userRepository.save(userEntity);
    }

    private boolean checkMandatoryFields(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getEmail().isBlank()
                || userRegistrationDto.getPassword().isBlank()
                || userRegistrationDto.getPasswordRepeated().isBlank();
    }

    private boolean checkPasswordCorrectness(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordRepeated());
    }
}
