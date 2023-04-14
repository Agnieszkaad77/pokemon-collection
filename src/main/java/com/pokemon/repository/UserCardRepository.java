package com.pokemon.repository;

import com.pokemon.entity.UserCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCardEntity, Long> {

}
