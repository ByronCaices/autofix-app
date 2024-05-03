package com.example.demo.controllers;

import com.example.demo.entities.OrderEntity;
import com.example.demo.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getBonusByRepairCode_ShouldReturnBonus() throws Exception {
        given(orderService.getBonusByRepairCode("repairCode")).willReturn(0.0f);

        mockMvc.perform(get("/api/v1/orders/bonus/{repairCode}", "repairCode"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("0.0"));
    }

    @Test
    public void saveOrder_ShouldReturnOrder() throws Exception {
        OrderEntity order = new OrderEntity("repairCode", "plate", "engine", "bodywork", 0.0f, 0.0f);
        given(orderService.closeRepairOrder(1L)).willReturn(order);

        mockMvc.perform(post("/api/v1/orders/{id}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.repairCode", is("repairCode")))
            .andExpect(jsonPath("$.plate", is("plate")))
            .andExpect(jsonPath("$.engine", is("engine")))
            .andExpect(jsonPath("$.bodywork", is("bodywork")))
            .andExpect(jsonPath("$.discBonus", is(0.0)))
            .andExpect(jsonPath("$.totalAmount", is(0.0)));
    }
}
