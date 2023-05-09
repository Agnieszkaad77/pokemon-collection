package com.pokemon.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder {

    public String encodePassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean verifyPassword(String loggingPassword, String encoded) {
        return BCrypt.verifyer().verify(loggingPassword.toCharArray(),encoded).verified;
    }
}
