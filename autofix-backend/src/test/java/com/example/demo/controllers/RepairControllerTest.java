package com.example.demo.controllers;

import com.example.demo.controllers.RepairController;
import com.example.demo.services.RepairService;
import com.example.demo.entities.RepairEntity;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepairController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairService repairService;

    @Test
    public void listRepairs_ShouldReturnRepairs() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        RepairEntity repair2 = new RepairEntity(1L,"HHHH88",
                "HHHH99",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepairs()).willReturn((ArrayList<RepairEntity>) repairList);

        mockMvc.perform(get("/api/v1/repairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("HHHH88")))
                .andExpect(jsonPath("$[1].plate", is("HHHH99")));
    }

    @Test
    public void getRepairById_ShouldReturnRepair() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair = new RepairEntity(1L,"HHHH88",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        given(repairService.getById(1L)).willReturn(repair);

        mockMvc.perform(get("/api/v1/repairs/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate", is("HHHH88")));
    }

    @Test
    public void getRepairsByRepairCode_ShouldReturnRepairs() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        RepairEntity repair2 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH99",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepairsByRepairCode("HHHH88ZZ")).willReturn((ArrayList<RepairEntity>) repairList);

        mockMvc.perform(get("/api/v1/repairs/code_HHHH88ZZ"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("HHHH88")))
                .andExpect(jsonPath("$[1].plate", is("HHHH99")));
    }

    @Test
    public void sumTotalAmountByRepairCode_ShouldReturnTotalAmount() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        RepairEntity repair2 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH99",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.sumTotalAmountByRepairCode("HHHH88ZZ")).willReturn(2000.0f);

        mockMvc.perform(get("/api/v1/repairs/totalAmount_HHHH88ZZ"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(2000.0)));
    }

    @Test
    public void getRepairTypeAmounts_ShouldReturnRepairTypeAmounts() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        RepairEntity repair2 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH99",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepairTypeAmounts()).willReturn(new ArrayList<Object[]>());

        mockMvc.perform(get("/api/v1/repairs/repairTypeAmounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getRepairTypeAmountsByEngine_ShouldReturnRepairTypeAmounts() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
        "HHHH88",
        "gas",
        "suv",
        "toyota",
        1,
        10000L,
        date1,
        date2,
        date3,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        0);

        RepairEntity repair2 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH99",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepairTypeAmountsByEngine()).willReturn(new ArrayList<Object[]>());

        mockMvc.perform(get("/api/v1/repairs/repairTypeAmountsByEngine"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }


}
