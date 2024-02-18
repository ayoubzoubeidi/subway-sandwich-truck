package com.example.subwaysandwichtruck.service;

import com.example.subwaysandwichtruck.web.dto.PlaceOrderRequest;

public interface OrderService {
    Long placeOrder(PlaceOrderRequest placeOrderRequest, Long userId);
}
