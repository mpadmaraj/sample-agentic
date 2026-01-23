package com.example.agentic.dao;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    public String findStatus(Long id) {
        return "CREATED";
    }
}
