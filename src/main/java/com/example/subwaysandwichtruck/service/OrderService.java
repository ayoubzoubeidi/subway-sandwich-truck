package com.example.subwaysandwichtruck.service;

import com.example.subwaysandwichtruck.web.dto.PlaceOrderRequest;

import java.math.BigDecimal;

public interface OrderService {
    Long placeOrder(PlaceOrderRequest placeOrderRequest, Long userId);
}
