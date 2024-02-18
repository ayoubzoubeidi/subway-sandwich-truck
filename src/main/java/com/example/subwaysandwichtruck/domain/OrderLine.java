package com.example.subwaysandwichtruck.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderLine {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Integer quantity;

    private BigDecimal linePrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
