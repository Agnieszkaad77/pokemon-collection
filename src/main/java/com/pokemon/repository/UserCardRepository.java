package com.pokemon.repository;

import com.pokemon.entity.UserCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCardRepository extends JpaRepository<UserCardEntity, Long> {

}
