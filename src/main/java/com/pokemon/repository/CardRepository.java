package com.pokemon.repository;

import com.pokemon.entity.CardDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardDataEntity, String> {

}
