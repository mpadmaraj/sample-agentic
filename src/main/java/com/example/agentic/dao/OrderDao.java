package com.example.agentic.dao;

import com.example.agentic.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    public Order findById(Long id) {
        return new Order(id, "CREATED", "Order for customer ID: " + id);
    }
}
