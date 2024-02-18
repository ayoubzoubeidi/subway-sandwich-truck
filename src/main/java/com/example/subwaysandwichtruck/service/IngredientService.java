package com.example.subwaysandwichtruck.service;

import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IngredientService {
    Page<IngredientDto> listIngredients(PageRequest pageRequest);
}
