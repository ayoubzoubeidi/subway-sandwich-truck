package com.example.subwaysandwichtruck.domain;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ingredient {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Integer availableQuantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private IngredientType ingredientType;
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines = new HashSet<>();
}
