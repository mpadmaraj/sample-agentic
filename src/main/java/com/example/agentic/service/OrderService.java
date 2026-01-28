package com.example.agentic.service;

import com.example.agentic.dao.OrderDao;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDao dao;

    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public String getStatus(Long id) {
        return dao.findStatus(id);
    }
}
