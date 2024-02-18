package com.example.subwaysandwichtruck.service.impl;

import com.example.subwaysandwichtruck.repository.IngredientRepository;
import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {


    @Mock
    IngredientRepository ingredientRepository;
    @InjectMocks
    IngredientServiceImpl ingredientService;
    Page<IngredientDto> listIngredientsResponse;
    IngredientDto ingredientDto = new IngredientDto(1L, "meat", new BigDecimal("55"), 5);
    PageRequest pageRequest = PageRequest.of(1, 25);

    @BeforeEach
    public void setup() {
        listIngredientsResponse =
                new PageImpl<>(List.of(ingredientDto), pageRequest, 1);
    }

    @Test
    public void testFindAllIngredients() {
        when(ingredientRepository.findAllPage(pageRequest)).thenReturn(listIngredientsResponse);

        Page<IngredientDto> ingredientsPage = ingredientService.listIngredients(pageRequest);

        assertNotNull(ingredientsPage);

        assertEquals(listIngredientsResponse.getTotalElements(), ingredientsPage.getTotalElements());

    }

}