package com.example.subwaysandwichtruck.web.dto;

import java.math.BigDecimal;

public record IngredientDto(Long id, String name, BigDecimal price, Integer quantity) {
}
