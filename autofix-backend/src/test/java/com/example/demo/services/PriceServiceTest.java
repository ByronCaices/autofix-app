package com.example.demo.services;

import com.example.demo.entities.PriceEntity;
import com.example.demo.repositories.PriceRepository;
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

@WebMvcTest(PriceService.class)
public class PriceServiceTest {

    @Autowired
    PriceService priceService;

    @MockBean
    PriceRepository priceRepository;

    @Test
    void whenGetPrices_thenCorrect() {
        //Given
        PriceEntity price = new PriceEntity();
        price.setId(1L);
        price.setEngine("gas");
        price.setRepairType(1);
        price.setPrice(1000);

        //When
        List<PriceEntity> priceList = new ArrayList<>();
        priceList.add(price);
        when(priceService.getPrices()).thenReturn(priceList);

        //Then
        assertNotNull(priceList);
        assertThat(priceList.size()).isEqualTo(1);
        assertThat(priceList.get(0).getEngine()).isEqualTo("gas");
        assertThat(priceList.get(0).getId()).isEqualTo(1L);
        assertThat(priceList.get(0).getRepairType()).isEqualTo(1);
        assertThat(priceList.get(0).getPrice()).isEqualTo(1000);
    }

    @Test
    void whenGetPriceByRepairTypeAndEngine_thenCorrect() {
        //Given
        PriceEntity price = new PriceEntity();
        price.setId(1L);
        price.setEngine("gas");
        price.setRepairType(1);
        price.setPrice(1000);

        //When
        when(priceService.getPriceByRepairTypeAndEngine("gas", 1)).thenReturn(1000);

        //Then
        assertThat(priceService.getPriceByRepairTypeAndEngine("gas", 1)).isEqualTo(1000);
    }
}
