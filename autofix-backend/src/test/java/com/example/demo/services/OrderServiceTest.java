package com.example.demo.services;
import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.CarRepository;
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

@WebMvcTest(OrderService.class)
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    RepairService repairService;

    @MockBean
    DiscountBonusService discountBonusService;

    @Test
    void whenGetBonusByRepairCode_thenCorrect() {
        //Given
        when(orderRepository.getBonusByRepairCode("repairCode")).thenReturn(0.0f);

        //When
        float result = orderService.getBonusByRepairCode("repairCode");

        //Then
        assertThat(result).isEqualTo(0.0f);
    }

    @Test
    void whenCloseRepairOrder_thenCorrect() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setBrand("Toyota");
        repair.setRepairCode("repairCode");
        repair.setPlate("HHHH88");
        repair.setEngine("V8");
        repair.setBodywork("Sedan");

        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setBrand("Toyota");
        discountBonus.setStock(1);
        discountBonus.setBonus(0);

        when(repairService.getById(1L)).thenReturn(repair);
        when(discountBonusService.getStockByBrand("Toyota")).thenReturn(1);
        when(discountBonusService.getBonusByBrand("Toyota")).thenReturn(0);
        when(repairService.sumTotalAmountByRepairCode("repairCode")).thenReturn(0.0f);
        when(orderRepository.save(new OrderEntity("repairCode", "HHHH88", "V8", "Sedan", 0.0f, 0.0f))).thenReturn(new OrderEntity("repairCode", "HHHH88", "V8", "Sedan", 0.0f, 0.0f));

        //When
        OrderEntity result = orderService.closeRepairOrder(1L);

        //Then
        assertNotNull(result);
        assertThat(result.getRepairCode()).isEqualTo("repairCode");
        assertThat(result.getPlate()).isEqualTo("HHHH88");
        assertThat(result.getEngine()).isEqualTo("V8");
        assertThat(result.getBodywork()).isEqualTo("Sedan");
        assertThat(result.getDiscBonus()).isEqualTo(0);
        assertThat(result.getTotalAmount()).isEqualTo(0);
    }

    @Test
    void whenCloseRepairOrder_thenCorrect2() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setBrand("Toyota");
        repair.setRepairCode("repairCode");
        repair.setPlate("HHHH88");
        repair.setEngine("V8");
        repair.setBodywork("Sedan");

        DiscountBonusEntity discountBonus = new DiscountBonusEntity();
        discountBonus.setBrand("Toyota");
        discountBonus.setStock(1);
        discountBonus.setBonus(0);

        when(repairService.getById(1L)).thenReturn(repair);
        when(discountBonusService.getStockByBrand("Toyota")).thenReturn(0);
        when(discountBonusService.getBonusByBrand("Toyota")).thenReturn(0);
        when(repairService.sumTotalAmountByRepairCode("repairCode")).thenReturn(0.0f);
        when(orderRepository.save(new OrderEntity("repairCode", "HHHH88", "V8", "Sedan", 0.0f, 0.0f))).thenReturn(new OrderEntity("repairCode", "HHHH88", "V8", "Sedan", 0, 0));

        //When
        OrderEntity result = orderService.closeRepairOrder(1L);

        //Then
        assertNotNull(result);
        assertThat(result.getRepairCode()).isEqualTo("repairCode");
        assertThat(result.getPlate()).isEqualTo("HHHH88");
        assertThat(result.getEngine()).isEqualTo("V8");
        assertThat(result.getBodywork()).isEqualTo("Sedan");
        assertThat(result.getDiscBonus()).isEqualTo(0.0f);
        assertThat(result.getTotalAmount()).isEqualTo(0.0f);
    }
}
