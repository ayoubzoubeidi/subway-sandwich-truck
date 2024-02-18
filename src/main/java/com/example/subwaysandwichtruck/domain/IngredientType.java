package com.example.subwaysandwichtruck.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class IngredientType {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredientType", fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();
}
