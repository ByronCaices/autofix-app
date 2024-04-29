package com.example.demo.controllers;


import com.example.demo.entities.SurchargeCarAgeEntity;
import com.example.demo.entities.SurchargeMileageEntity;
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

@WebMvcTest(SurchargeMileageController.class)
public class SurchargeMileageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurchargeMileageService surchargeMileageService;

    @Test
    public void listSurchargesMileages_ShouldReturnSurcharges() throws Exception {
        SurchargeMileageEntity surch1 = new SurchargeMileageEntity(1L,
                "B",
                "sedan",
                0.03f);

        SurchargeMileageEntity surch2 = new SurchargeMileageEntity();
        surch2.setId(2L);
        surch2.setCategory("D");
        surch2.setBodywork("sedan");
        surch2.setSurcharge(0.12f);

        List<SurchargeMileageEntity> surchList = new ArrayList<>(Arrays.asList(surch1, surch2));

        given(surchargeMileageService.listSurchargesMileages()).willReturn((ArrayList<SurchargeMileageEntity>) surchList);

        mockMvc.perform(get("/api/v1/surchmileages/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].surcharge", is(0.03)))
                .andExpect(jsonPath("$[1].surcharge", is(0.12)));
    }

    @Test
    public void getDiscountByCategoryAndEngine_ShouldReturnFloat() throws Exception {
        SurchargeMileageEntity surch1 = new SurchargeMileageEntity(1L,
                "B",
                "sedan",
                0.03f);

        given(surchargeMileageService.getSurchargeMileageByCategoryAndBodywork("B","sedan")).willReturn(0.03f);

        mockMvc.perform(get("/api/v1/surchmileages/B/sedan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(0.03)));
    }
}
