package com.pokemon.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String email;
    private String password;
    private String passwordRepeated;
    private boolean agree;
}
