package com.example.subwaysandwichtruck.web.controllers;

import com.example.subwaysandwichtruck.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/ingredient/{date}")
    public ResponseEntity<?> getBestSellingIngredient(@PathVariable LocalDate date) {
        return ResponseEntity.ok(statisticsService.getBestSellingIngredient(date));
    }

    @GetMapping("/profit/{date}")
    public ResponseEntity<?> getProfitForDay(@PathVariable LocalDate date) {
        return ResponseEntity.ok(statisticsService.getProfitForSpecificDay(date));
    }
    @GetMapping("/orders/{date}")
    public ResponseEntity<?> getNumberOfOrders(@PathVariable LocalDate date) {
        return ResponseEntity.ok(statisticsService.getProfitForSpecificDay(date));
    }

}
