package com.example.demo.controllers;

import com.example.demo.entities.CarEntity;
import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.services.DiscountBonusService;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiscountBonusController.class)
public class DiscountBonusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountBonusService discountBonusService;

    @Test
    public void getDiscountBonus_ShouldReturnDiscountBonuses() throws Exception{
        DiscountBonusEntity dbonus1 = new  DiscountBonusEntity(1L,
                "chevrolet",
                20000,
                5);

        DiscountBonusEntity dbonus2 = new  DiscountBonusEntity(2L,
                "kia",
                15000,
                4);

        List<DiscountBonusEntity> discBonusList = new ArrayList<>(Arrays.asList(dbonus1,dbonus2));
        given(discountBonusService.getDiscountBonus()).willReturn((ArrayList<DiscountBonusEntity>)discBonusList);

        mockMvc.perform(get("/api/v1/discbonus/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("chevrolet")))
                .andExpect(jsonPath("$[1].brand", is("kia")));
    }

    @Test
    public void getDiscountBonusById_ShouldReturnDiscBonus() throws Exception {

        DiscountBonusEntity dbonus = new  DiscountBonusEntity(1L,
                "chevrolet",
                20000,
                5);

        given(discountBonusService.getDiscountBonusById(1L)).willReturn(dbonus);

        mockMvc.perform(get("/api/v1/discbonus/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand", is("chevrolet")));
    }

    @Test
    public void deleteDiscountBonusByBrand_ShouldReturn200() throws Exception {
        // ID de prueba para el descuento a eliminar
        Long discountId = 1L;

        // Simular la petición DELETE
        mockMvc.perform(delete("/api/v1/discbonus/{id}", discountId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());  // Verificar que la respuesta es 200 OK

        // Verificar que el servicio fue llamado exactamente una vez con el ID correcto
        verify(discountBonusService, times(1)).deleteDiscountBonusByBrand(discountId);
    }

    @Test
    public void saveDiscountBonus_ShouldReturnSavedDiscountBonus() throws Exception {
        DiscountBonusEntity discountBonus = new DiscountBonusEntity(1L,"chevrolet",20000,6);

        String discBonusJson = """
                {
                    "brand":"chevrolet",
                    "bonus":20000,
                    "stock":6
                }
                """;

        // Simula la respuesta del servicio
        when(discountBonusService.saveDiscountBonus(discountBonus)).thenReturn(discountBonus);

        // Simula la petición POST
        mockMvc.perform(post("/api/v1/discbonus/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(discBonusJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateDiscountBonus_ShouldReturnSavedDiscountBonus() throws Exception {
        DiscountBonusEntity discountBonus = new DiscountBonusEntity(1L,"chevrolet",20000,6);

        String discBonusJson = """
                {
                    "brand":"chevrolet",
                    "bonus":20000,
                    "stock":6
                }
                """;

        // Simula la respuesta del servicio
        when(discountBonusService.saveDiscountBonus(discountBonus)).thenReturn(discountBonus);

        // Simula la petición
        mockMvc.perform(put("/api/v1/discbonus/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(discBonusJson))
                .andExpect(status().isOk());
    }



}
