package com.example.subwaysandwichtruck.repository;

import com.example.subwaysandwichtruck.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
