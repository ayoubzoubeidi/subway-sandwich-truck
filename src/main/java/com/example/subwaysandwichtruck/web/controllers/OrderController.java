package com.example.subwaysandwichtruck.web.controllers;

import com.example.subwaysandwichtruck.service.OrderService;
import com.example.subwaysandwichtruck.web.dto.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // TODO change userId request param when implementing spring security to fetch the user from the context;
    @PostMapping("/{userId}")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest, @PathVariable Long userId) {

        Long bookingId = orderService.placeOrder(placeOrderRequest, userId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingId.toString())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
