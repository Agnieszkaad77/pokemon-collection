package com.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class CardDataEntity {

    @Id
    private String id;
    private String name;
    private String largeImage;

}
