package com.example.subwaysandwichtruck.service.impl;

import com.example.subwaysandwichtruck.repository.IngredientRepository;
import com.example.subwaysandwichtruck.repository.OrderRepository;
import com.example.subwaysandwichtruck.service.StatisticsService;
import com.example.subwaysandwichtruck.web.dto.IngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderRepository orderRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientDto getBestSellingIngredient(LocalDate date) {
        var ingredient = ingredientRepository.getBestSellingIngredient(date);
        System.err.println(ingredient.name());
        return ingredientRepository.getBestSellingIngredient(date);
    }

    @Override
    public Long getNumberOfSoldSandwiches(LocalDate date) {
        return orderRepository.numberOfOrderByDay(date);
    }

    @Override
    public BigDecimal getProfitForSpecificDay(LocalDate date) {
        return orderRepository.profitForDay(date);
    }
}
