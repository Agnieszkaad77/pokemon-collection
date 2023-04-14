package com.pokemon.mapper;

import com.pokemon.dto.UserDto;
import com.pokemon.dto.UserLoginDto;
import com.pokemon.dto.UserRegistrationDto;
import com.pokemon.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private UserCardMapper userCardMapper;

    public UserLoginDto toUserLoginDto(UserEntity userEntity) {
        return UserLoginDto.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity toUserEntity(UserRegistrationDto userRegistrationDto) {
        return UserEntity.builder()
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .agree(userRegistrationDto.isAgree())
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .pokeCoins(userEntity.getPokeCoins())
                .points(userEntity.getPoints())
                .cards((userEntity.getCards().stream()
                        .filter(userCardEntity -> userCardEntity.isVisibleInCollection())
                        .map(userCardEntity -> userCardMapper.toUserCardDto(userCardEntity))
                        .collect(Collectors.toList())))
                .build();
    }
}
