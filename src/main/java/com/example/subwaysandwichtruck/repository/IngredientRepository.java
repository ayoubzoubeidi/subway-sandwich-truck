package com.example.subwaysandwichtruck.repository;

import com.example.subwaysandwichtruck.domain.Ingredient;
import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("""
            SELECT new com.example.subwaysandwichtruck.web.dto.IngredientDto(ingredient.id, ingredient.name,
            ingredient.price, ingredient.availableQuantity) FROM Ingredient ingredient
            """)
    Page<IngredientDto> findAllPage(PageRequest pageRequest);

    @Query("""
            SELECT new com.example.subwaysandwichtruck.web.dto.IngredientDto(ingredient.id, ingredient.name,
            ingredient.price, ingredient.availableQuantity)
            FROM Ingredient ingredient
            LEFT JOIN ingredient.orderLines orderLine
            WHERE DATE(orderLine.order.createdAt) = :localDate
            GROUP BY ingredient.id
            HAVING SUM(orderLine.quantity) >= ALL (SELECT SUM(ol.quantity)
            FROM OrderLine ol WHERE DATE(ol.order.createdAt) = :localDate
            GROUP BY ol.ingredient.id)
            """)
    IngredientDto getBestSellingIngredient(@Param("localDate") LocalDate localDate);
}
