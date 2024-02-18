package com.example.subwaysandwichtruck.service.impl;

import com.example.subwaysandwichtruck.domain.ApplicationUser;
import com.example.subwaysandwichtruck.domain.Ingredient;
import com.example.subwaysandwichtruck.domain.Order;
import com.example.subwaysandwichtruck.repository.ApplicationUserRepository;
import com.example.subwaysandwichtruck.repository.IngredientRepository;
import com.example.subwaysandwichtruck.repository.OrderRepository;
import com.example.subwaysandwichtruck.web.dto.OrderLineRequest;
import com.example.subwaysandwichtruck.web.dto.PlaceOrderRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void placeValidOrder() {
        ApplicationUser user = ApplicationUser.builder().id(1L).build();

        when(applicationUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(Ingredient.builder().id(1L).name("Test Ingredient").price(BigDecimal.valueOf(1.0)).build()));
        when(orderRepository.saveAndFlush(any())).thenReturn(Order.builder().id(1L).build());

        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest(Collections.singletonList(new OrderLineRequest(1L, 2)));
        Long orderId = orderService.placeOrder(placeOrderRequest, 1L);

        assertEquals(1L, orderId);
        verify(orderRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void placeOrderWithoutExistingUser() {
        when(applicationUserRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.placeOrder(new PlaceOrderRequest(Collections.singletonList(new OrderLineRequest(1L, 2))), 1L));
    }
}
