package com.example.demo.controllers;

import com.example.demo.entities.DiscountRegClientEntity;

import com.example.demo.services.DiscountRegClientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiscountRegClientController.class)
public class DiscountRegClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountRegClientService discountRegClientService;

    @Test
    public void listDiscount_ShouldReturnDiscounts() throws Exception {
        DiscountRegClientEntity disc1 = new DiscountRegClientEntity(1L,
                "A",
                "gas",
                0.05f);

        DiscountRegClientEntity disc2 = new DiscountRegClientEntity();
        disc2.setId(2L);
        disc2.setCategory("B");
        disc2.setEngine("gas");
        disc2.setDiscount(0.1f);


        List<DiscountRegClientEntity> discList = new ArrayList<>(Arrays.asList(disc1, disc2));

        given(discountRegClientService.getDiscountRegClients()).willReturn((ArrayList<DiscountRegClientEntity>) discList);

        mockMvc.perform(get("/api/v1/discregclients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].discount", is(0.05)))
                .andExpect(jsonPath("$[1].discount", is(0.1)));
    }

    @Test
    public void getDiscountByCategoryAndEngine_ShouldReturnFloat() throws Exception {
        DiscountRegClientEntity disc1 = new DiscountRegClientEntity(1L,
                "A",
                "gas",
                0.05f);

        given(discountRegClientService.getDiscountByCategoryAndEngine("A","gas")).willReturn(0.05f);

        mockMvc.perform(get("/api/v1/discregclients/A/gas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(0.05)));
    }

}
