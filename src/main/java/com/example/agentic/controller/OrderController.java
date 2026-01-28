package com.example.agentic.controller;

import com.example.agentic.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        return service.getStatus(id);
    }
}
