package com.example.demo.services;

import com.example.demo.entities.CarEntity;
import com.example.demo.entities.DiscountRegClientEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.repositories.DiscountRegClientRepository;
import com.example.demo.repositories.RepairRepository;
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

@WebMvcTest(DiscountRegClientService.class)
public class DiscountRegClientServiceTest {

    @Autowired
    DiscountRegClientService discountRegClientService;

    @MockBean
    DiscountRegClientRepository discountRegClientRepository;

    @Test
    public void whenGetDiscountRegClients_thenReturnDiscountRegClients() {
        // given
        DiscountRegClientEntity discountRegClientEntity = new DiscountRegClientEntity(1L, "A", "diesel", 0.1f);
        List<DiscountRegClientEntity> discountRegClientEntities = new ArrayList<>();
        discountRegClientEntities.add(discountRegClientEntity);
        when(discountRegClientRepository.findAll()).thenReturn(discountRegClientEntities);

        // when
        List<DiscountRegClientEntity> found = discountRegClientService.getDiscountRegClients();

        // then
        assertNotNull(found);
        assertThat(found).hasSize(1);
    }

    @Test
    public void whenGetDiscountByCategoryAndEngine_thenReturnDiscount() {
        // given
        String category = "A";
        String engine = "diesel";
        float discount = 0.1f;
        when(discountRegClientRepository.findDiscountRCByCategoryAndEngine(category, engine)).thenReturn(discount);

        // when
        float found = discountRegClientService.getDiscountByCategoryAndEngine(category, engine);

        // then
        assertThat(found).isEqualTo(discount);
    }


}
