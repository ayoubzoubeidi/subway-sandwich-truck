package com.example.subwaysandwichtruck.repository;

import com.example.subwaysandwichtruck.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(""" 
            SELECT SUM(o.totalPrice) FROM Order o WHERE DATE(o.createdAt) = :localDate
            """)
    BigDecimal profitForDay(@Param("localDate") LocalDate localDate);

    @Query(""" 
            SELECT COUNT(o.id) FROM Order o WHERE DATE(o.createdAt) = :localDate
            """)
    Long numberOfOrderByDay(@Param("localDate") LocalDate localDate);

}
