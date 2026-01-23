package com.example.agentic.service;

import com.example.agentic.dao.OrderDao;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class OrderServiceTest {

    @Test
    void returnsStatus() {
        OrderService service = new OrderService(new OrderDao());
        assertThat(service.getStatus(1L)).isEqualTo("CREATED");
    }
}
