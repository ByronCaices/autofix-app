package com.example.demo.services;

import com.example.demo.entities.SurchargeCarAgeEntity;

import com.example.demo.repositories.SurchargeCarAgeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(SurchargeCarAgeService.class)
public class SurchargeCarAgeServiceTest {

    @Autowired
    SurchargeCarAgeService surchargeCarAgeService;

    @MockBean
    SurchargeCarAgeRepository surchargeCarAgeRepository;

    @Test
    void whenListSurchargesCarAges_thenCorrect() {
        //Given
        SurchargeCarAgeEntity surchargeCarAge = new SurchargeCarAgeEntity();
        surchargeCarAge.setId(1L);
        surchargeCarAge.setCategory("Sedan");
        surchargeCarAge.setBodywork("Sedan");
        surchargeCarAge.setSurcharge(1000);

        //When
        List<SurchargeCarAgeEntity> surchargeCarAgeList = new ArrayList<>();
        surchargeCarAgeList.add(surchargeCarAge);
        when(surchargeCarAgeService.listSurchargesCarAges()).thenReturn(surchargeCarAgeList);

        //Then
        assertNotNull(surchargeCarAgeList);
        assertThat(surchargeCarAgeList.size()).isEqualTo(1);
        assertThat(surchargeCarAgeList.get(0).getCategory()).isEqualTo("Sedan");
        assertThat(surchargeCarAgeList.get(0).getId()).isEqualTo(1L);
        assertThat(surchargeCarAgeList.get(0).getBodywork()).isEqualTo("Sedan");
        assertThat(surchargeCarAgeList.get(0).getSurcharge()).isEqualTo(1000);
    }

    @Test
    void whenGetSurchargeByCategoryAndBodywork_thenCorrect() {
        //Given
        SurchargeCarAgeEntity surchargeCarAge = new SurchargeCarAgeEntity();
        surchargeCarAge.setId(1L);
        surchargeCarAge.setCategory("Sedan");
        surchargeCarAge.setBodywork("Sedan");
        surchargeCarAge.setSurcharge(1000);

        //When
        when(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("Sedan", "Sedan")).thenReturn(1000f);

        //Then
        assertThat(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("Sedan", "Sedan")).isEqualTo(1000f);
    }


}
