package com.example.subwaysandwichtruck.service.impl;

import com.example.subwaysandwichtruck.domain.ApplicationUser;
import com.example.subwaysandwichtruck.domain.Ingredient;
import com.example.subwaysandwichtruck.domain.Order;
import com.example.subwaysandwichtruck.domain.OrderLine;
import com.example.subwaysandwichtruck.repository.ApplicationUserRepository;
import com.example.subwaysandwichtruck.repository.IngredientRepository;
import com.example.subwaysandwichtruck.repository.OrderRepository;
import com.example.subwaysandwichtruck.service.OrderService;
import com.example.subwaysandwichtruck.web.dto.OrderLineRequest;
import com.example.subwaysandwichtruck.web.dto.PlaceOrderRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final IngredientRepository ingredientRepository;
    private static final BigDecimal PROFIT_MARGIN = new BigDecimal("1.3");


    @Override
    @Transactional
    public Long placeOrder(PlaceOrderRequest placeOrderRequest, Long userId) {

        Optional<ApplicationUser> userOptional = applicationUserRepository.findById(userId);

        // TODO change with custom exceptions.
        ApplicationUser user = userOptional.orElseThrow(RuntimeException::new);

        if (placeOrderRequest.orderLine() == null || placeOrderRequest.orderLine().isEmpty()) {
            throw new RuntimeException("The ingredients must not be null");
        }

        Order order = Order.builder().user(user).build();

        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(new BigDecimal("0"));

        Set<OrderLine> orderLines =
                placeOrderRequest.orderLine()
                        .stream()
                        .map(orderLineRequest -> {
                            OrderLine orderLine = mapOrderLineRequestToEntity(orderLineRequest);
                            orderLine.setOrder(order);
                            totalPrice.set(totalPrice.get().add(orderLine.getLinePrice()));
                            return orderLine;
                        })
                        .collect(Collectors.toSet());

        order.setOrderLines(orderLines);
        order.setTotalPrice(totalPrice.get().multiply(PROFIT_MARGIN));
        return orderRepository.saveAndFlush(order).getId();
    }

    private OrderLine mapOrderLineRequestToEntity(OrderLineRequest orderLineRequest) {
        Ingredient ingredient = ingredientRepository
                .findById(orderLineRequest.ingredientId()).orElseThrow(RuntimeException::new);

        return OrderLine
                .builder()
                .ingredient(ingredient)
                .quantity(orderLineRequest.quantity())
                .linePrice(ingredient.getPrice().multiply(BigDecimal.valueOf(orderLineRequest.quantity())))
                .build();
    }
}
