package com.example.demo.controllers;

import com.example.demo.entities.PriceEntity;
import com.example.demo.entities.SurchargeCarAgeEntity;
import com.example.demo.entities.SurchargeMileageEntity;
import com.example.demo.services.PriceService;
import com.example.demo.services.SurchargeCarAgeService;
import com.example.demo.services.SurchargeMileageService;
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

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PriceService priceService;

        @Test
        public void listPrices_ShouldReturnPrices() throws Exception {
            PriceEntity price1 = new PriceEntity(1L,
                    1,
                    "gas",
                    120);

            PriceEntity price2 = new PriceEntity(2L,
                    2,
                    "gas",
                    130);

            List<PriceEntity> priceList = new ArrayList<>(Arrays.asList(price1, price2));

            given(priceService.getPrices()).willReturn((ArrayList<PriceEntity>) priceList);

            mockMvc.perform(get("/api/v1/prices/"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].price", is(120)))
                    .andExpect(jsonPath("$[1].price", is(130)));
        }

        @Test
        public void getPriceByRepairTypeAndEngine_ShouldReturnInteger() throws Exception {
            PriceEntity price1 = new PriceEntity(1L,
                    1,
                    "gas",
                    120);

            PriceEntity price2 = new PriceEntity();
            price2.setId(2L);
            price2.setRepairType(2);
            price2.setEngine("gas");
            price2.setPrice(130);

            List<PriceEntity> priceList = new ArrayList<>(Arrays.asList(price1, price2));

            given(priceService.getPriceByRepairTypeAndEngine("gas", 1)).willReturn(120);

            mockMvc.perform(get("/api/v1/prices/gas/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", is(120)));
        }
}
