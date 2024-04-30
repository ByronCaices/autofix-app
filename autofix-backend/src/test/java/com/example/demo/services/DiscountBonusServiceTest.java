package com.example.demo.services;

import com.example.demo.entities.CarEntity;
import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.repositories.DiscountBonusRepository;
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

@WebMvcTest(DiscountBonusService.class)
public class DiscountBonusServiceTest {

    @Autowired
    DiscountBonusService discountBonusService;

    @MockBean
    DiscountBonusRepository discountBonusRepository;

    @Test
    void whenGetDiscountBonus_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        List<DiscountBonusEntity> discountBonusList = new ArrayList<>();
        discountBonusList.add(discountBonus);
        when(discountBonusService.getDiscountBonus()).thenReturn(discountBonusList);

        //Then
        assertNotNull(discountBonusList);
        assertThat(discountBonusList.size()).isEqualTo(1);
        assertThat(discountBonusList.get(0).getBrand()).isEqualTo("Toyota");
        assertThat(discountBonusList.get(0).getId()).isEqualTo(1L);
        assertThat(discountBonusList.get(0).getBonus()).isEqualTo(1000);
        assertThat(discountBonusList.get(0).getStock()).isEqualTo(10);
    }

    @Test
    void whenSaveDiscountBonus_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusService.saveDiscountBonus(discountBonus)).thenReturn(discountBonus);

        //Then
        DiscountBonusEntity savedDiscountBonus = discountBonusService.saveDiscountBonus(discountBonus);
        assertNotNull(savedDiscountBonus);
        assertThat(savedDiscountBonus.getBrand()).isEqualTo("Toyota");
        assertThat(savedDiscountBonus.getId()).isEqualTo(1L);
        assertThat(savedDiscountBonus.getBonus()).isEqualTo(1000);
        assertThat(savedDiscountBonus.getStock()).isEqualTo(10);
    }

    @Test
    void whenGetByBrand_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusService.getByBrand("Toyota")).thenReturn(discountBonus);

        //Then
        DiscountBonusEntity foundDiscountBonus = discountBonusService.getByBrand("Toyota");
        assertNotNull(foundDiscountBonus);
        assertThat(foundDiscountBonus.getBrand()).isEqualTo("Toyota");
        assertThat(foundDiscountBonus.getId()).isEqualTo(1L);
        assertThat(foundDiscountBonus.getBonus()).isEqualTo(1000);
        assertThat(foundDiscountBonus.getStock()).isEqualTo(10);
    }

    @Test
    void whenGetDiscountBonusById_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusRepository.findById(1L)).thenReturn(Optional.of(discountBonus));

        //Then
        DiscountBonusEntity foundDiscountBonus = discountBonusService.getDiscountBonusById(1L);
        assertThat(discountBonus.getBrand()).isEqualTo("Toyota");
        assertThat(discountBonus.getId()).isEqualTo(1L);
        assertThat(discountBonus.getBonus()).isEqualTo(1000);
        assertThat(discountBonus.getStock()).isEqualTo(10);
    }

    @Test
    void whenGetBonusByBrand_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusService.getBonusByBrand("Toyota")).thenReturn(1000);

        //Then
        int bonus = discountBonusService.getBonusByBrand("Toyota");
        assertThat(bonus).isEqualTo(1000);
    }

    @Test
    void whenDecreaseStockByBrand_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusService.getByBrand("Toyota")).thenReturn(discountBonus);

        //Then
        discountBonusService.decreaseStockByBrand("Toyota");
        assertThat(discountBonus.getStock()).isEqualTo(9);
    }

    @Test
    void whenDeleteDiscountBonusByBrand_thenCorrect() {
        //Given
        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setId(1L);
        discountBonus.setBrand("Toyota");
        discountBonus.setBonus(1000);
        discountBonus.setStock(10);

        //When
        when(discountBonusRepository.findById(1L)).thenReturn(null);

        //Then
        discountBonusService.deleteDiscountBonusByBrand(1L);
        assertThat(discountBonusRepository.findById(1L)).isNull();
    }


}
