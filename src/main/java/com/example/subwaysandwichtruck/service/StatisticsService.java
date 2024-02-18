package com.example.subwaysandwichtruck.service;

import com.example.subwaysandwichtruck.web.dto.IngredientDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface StatisticsService {

    IngredientDto getBestSellingIngredient(LocalDate date);

    Long getNumberOfSoldSandwiches(LocalDate date);
    BigDecimal getProfitForSpecificDay(LocalDate date);


}
