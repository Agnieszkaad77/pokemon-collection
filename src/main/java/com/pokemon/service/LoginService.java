package com.pokemon.service;

import com.pokemon.dto.SessionDto;
import com.pokemon.dto.UserDto;
import com.pokemon.dto.UserLoginDto;
import com.pokemon.entity.UserEntity;
import com.pokemon.exception.LoginException;
import com.pokemon.mapper.UserMapper;
import com.pokemon.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    @Resource(name = "sessionScopedBean")
    private SessionDto sessionDto;
    private UserRepository userRepository;
    private UserMapper userMapper;


    public void login(UserLoginDto userLoginDto) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userLoginDto.getEmail());
        UserEntity userEntity = userOptional.orElseThrow(
                () -> new LoginException("Password or email is not correct"));
        if (!userEntity.getPassword().equals(userLoginDto.getPassword())) {
            throw new LoginException("Password or email is not correct");
        }
        sessionDto.login(userEntity);
    }

    public UserDto getLoggedUserDto() {
        if (sessionDto.getUser() == null) {
            throw new LoginException("User is not logged!");
        }
        return userMapper.toUserDto(sessionDto.getUser());
    }

    UserEntity getLoggedUserEntity() {
        if (sessionDto.getUser() == null) {
            throw new LoginException("User is not logged!");
        }
        return sessionDto.getUser();
    }
}
