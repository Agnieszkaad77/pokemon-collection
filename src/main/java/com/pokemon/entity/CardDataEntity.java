package com.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "card_data")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Builder
public class CardDataEntity {

    @Id
    private String id;
    private String name;
    private String largeImage;

}
