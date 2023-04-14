package com.pokemon.configuration;

import com.pokemon.dto.SessionDto;
import com.pokemon.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfiguration {

    @Bean
    @SessionScope
    public SessionDto sessionScopedBean(UserRepository userRepository) {
        return new SessionDto(userRepository);
    }
}
