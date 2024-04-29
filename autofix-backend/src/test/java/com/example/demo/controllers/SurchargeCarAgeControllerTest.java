package com.example.demo.controllers;


import com.example.demo.entities.SurchargeCarAgeEntity;
import com.example.demo.services.SurchargeCarAgeService;
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

@WebMvcTest(SurchargeCarAgeController.class)
public class SurchargeCarAgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurchargeCarAgeService surchargeCarAgeService;

    @Test
    public void listSurchargesCarAges_ShouldReturnSurcharges() throws Exception {
        SurchargeCarAgeEntity surch1 = new SurchargeCarAgeEntity(1L,
                "B",
                "sedan",
                0.05f);

        SurchargeCarAgeEntity surch2 = new SurchargeCarAgeEntity();
        surch2.setId(2L);
        surch2.setCategory("D");
        surch2.setBodywork("sedan");
        surch2.setSurcharge(0.15f);



        List<SurchargeCarAgeEntity> surchList = new ArrayList<>(Arrays.asList(surch1, surch2));

        given(surchargeCarAgeService.listSurchargesCarAges()).willReturn((ArrayList<SurchargeCarAgeEntity>) surchList);

        mockMvc.perform(get("/api/v1/surchcarages/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].surcharge", is(0.05)))
                .andExpect(jsonPath("$[1].surcharge", is(0.15)));
    }

    @Test
    public void getDiscountByCategoryAndEngine_ShouldReturnFloat() throws Exception {
        SurchargeCarAgeEntity disc1 = new SurchargeCarAgeEntity(1L,
                "B",
                "sedan",
                0.05f);

        given(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("B","sedan")).willReturn(0.05f);

        mockMvc.perform(get("/api/v1/surchcarages/B/sedan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(0.05)));
    }
}
