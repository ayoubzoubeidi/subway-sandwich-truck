package com.example.subwaysandwichtruck.web.controllers;

import com.example.subwaysandwichtruck.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final Integer DEFAULT_PAGE_NUMBER = 0;
    private final Integer DEFAULT_PAGE_SIZE = 25;

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<?> listIngredients(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return ResponseEntity.ok(ingredientService.listIngredients(PageRequest.of(pageNumber, pageSize)));
    }

}
