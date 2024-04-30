package com.example.demo.services;

import com.example.demo.entities.SurchargeMileageEntity;
import com.example.demo.repositories.SurchargeMileageRepository;
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

@WebMvcTest(SurchargeMileageService.class)
public class SurchargeMileageServiceTest {

    @Autowired
    SurchargeMileageService surchargeMileageService;

    @MockBean
    SurchargeMileageRepository surchargeMileageRepository;

    @Test
    public void whenListSurchargesMileages_thenCorrect() {
        //Given
        SurchargeMileageEntity surchargeMileage = new SurchargeMileageEntity();
        surchargeMileage.setId(1L);
        surchargeMileage.setCategory("A");
        surchargeMileage.setBodywork("Sedan");
        surchargeMileage.setSurcharge(1000);

        ArrayList<SurchargeMileageEntity> surchargeMileageList = new ArrayList<>();
        surchargeMileageList.add(surchargeMileage);

        //When
        when(surchargeMileageRepository.findAll()).thenReturn(surchargeMileageList);

        //Then
        List<SurchargeMileageEntity> result = surchargeMileageService.listSurchargesMileages();
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getCategory()).isEqualTo("A");
        assertThat(result.get(0).getBodywork()).isEqualTo("Sedan");
        assertThat(result.get(0).getSurcharge()).isEqualTo(1000);
    }

    @Test
    public void whenGetSurchargeMileageByCategoryAndBodywork_thenCorrect() {
        //Given
        SurchargeMileageEntity surchargeMileage = new SurchargeMileageEntity();
        surchargeMileage.setId(1L);
        surchargeMileage.setCategory("A");
        surchargeMileage.setBodywork("Sedan");
        surchargeMileage.setSurcharge(1000);

        //When
        when(surchargeMileageRepository.getSurchargeMileageByCategoryAndBodywork("A", "Sedan")).thenReturn(1000f);

        //Then
        float result = surchargeMileageService.getSurchargeMileageByCategoryAndBodywork("A", "Sedan");
        assertThat(result).isEqualTo(1000f);
    }
}
