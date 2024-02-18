package com.example.subwaysandwichtruck.web.dto;

import java.util.List;

public record PlaceOrderRequest(List<OrderLineRequest> orderLine) {
}
