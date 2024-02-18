package com.example.subwaysandwichtruck.service.impl;

import com.example.subwaysandwichtruck.repository.IngredientRepository;
import com.example.subwaysandwichtruck.service.IngredientService;
import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Page<IngredientDto> listIngredients(PageRequest pageRequest) {
        return ingredientRepository.findAllPage(pageRequest);
    }
}
