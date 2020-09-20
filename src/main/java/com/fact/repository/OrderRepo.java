package com.fact.repository;

import com.fact.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {


    Order findByOrderId(Integer order);
}