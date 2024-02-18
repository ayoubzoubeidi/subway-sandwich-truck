package com.example.subwaysandwichtruck.repository;

import com.example.subwaysandwichtruck.domain.Ingredient;
import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("""
            SELECT new com.example.subwaysandwichtruck.web.dto.IngredientDto(ingredient.id, ingredient.name, 
            ingredient.price, ingredient.availableQuantity) FROM Ingredient ingredient           
            """)
    Page<IngredientDto> findAllPage(PageRequest pageRequest);

}
