package com.example.demo.controllers;

import com.example.demo.controllers.RepairController;
import com.example.demo.services.RepairService;
import com.example.demo.entities.RepairEntity;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
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

        //Date date1 = formatter.parse("2021-01-01");
        //Date date2 = formatter.parse("2021-01-02");
        //Date date3 = formatter.parse("2021-01-03");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

    @Test
    public void getAverageRepairTimeByBrand_ShouldReturnAverageRepairTimeByBrand() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
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

        given(repairService.getAverageRepairTimeByBrand()).willReturn(new ArrayList<Object[]>());

        mockMvc.perform(get("/api/v1/repairs/averageRepairTimeByBrand"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void saveRepair_ShouldReturnSavedRepair() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
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

        String repairJson = """
                {
                    "plate": "HHHH88",
                    "engine": "",
                    "bodywork": "",
                    "brand": "",
                    "repairType": 1,
                    "repairCode": "rrr",
                    "mileage": 23456,
                    "checkinDate": "2024-05-02T10:12:39",
                    "finishDate": "",
                    "checkoutDate": "",
                    "repairPrice": 0.0,
                    "totalAmount": 0.0,
                    "discRegClient": 0.0,
                    "discBonus": 0.0,
                    "discMonThu": 0.0,
                    "surchCarage": 0.0,
                    "surchMileage": 0.0,
                    "surchDelay": 0.0,
                    "iva": 0.0
                }
                """;

        given(repairService.saveRepair(repair)).willReturn(repair);

        mockMvc.perform(post("/api/v1/repairs/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(repairJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRepair_ShouldReturnUpdatedRepair() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
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

        String repairJson = """
                {
                    "id": 1,
                    "plate": "HHHH88",
                    "engine": "",
                    "bodywork": "",
                    "brand": "",
                    "repairType": 1,
                    "repairCode": "rrr",
                    "mileage": 23456,
                    "checkinDate": "2024-05-02T10:12:39",
                    "finishDate": "",
                    "checkoutDate": "",
                    "repairPrice": 0.0,
                    "totalAmount": 0.0,
                    "discRegClient": 0.0,
                    "discBonus": 0.0,
                    "discMonThu": 0.0,
                    "surchCarage": 0.0,
                    "surchMileage": 0.0,
                    "surchDelay": 0.0,
                    "iva": 0.0
                }
                """;

        given(repairService.updateRepair(repair)).willReturn(repair);

        mockMvc.perform(put("/api/v1/repairs/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(repairJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRepair_ShouldReturnUpdatedRepairWithFinalPrice() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
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

        String repairJson = """
                {
                    "id": 1,
                    "plate": "HHHH88",
                    "engine": "",
                    "bodywork": "",
                    "brand": "",
                    "repairType": 1,
                    "repairCode": "rrr",
                    "mileage": 23456,
                    "checkinDate": "2024-04-19T14:00:00.000+00:00",
                    "finishDate": "2024-04-22T14:00:00.000+00:00",
                    "checkoutDate": "2024-04-24T14:00:00.000+00:00",
                    "repairPrice": 0.0,
                    "totalAmount": 0.0,
                    "discRegClient": 0.0,
                    "discBonus": 0.0,
                    "discMonThu": 0.0,
                    "surchCarage": 0.0,
                    "surchMileage": 0.0,
                    "surchDelay": 0.0,
                    "iva": 0.0
                }
                """;
        float totalAmount = 165648.0f;

        given(repairService.updateRepair(repair)).willReturn(repair);

        mockMvc.perform(put("/api/v1/repairs/calcFinalPrice/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(repairJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRepair_ShouldReturnDeletedRepair() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();
        LocalDateTime date3 = LocalDateTime.now();
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
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

        //given(repairService.deleteRepair(1L)).willReturn(repair);

        mockMvc.perform(delete("/api/v1/repairs/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRepair_ShouldReturnFalse_WhenAnErrorOccurs() throws Exception {

        doThrow(new RuntimeException("Error")).when(repairService).deleteRepair(1L);


        mockMvc.perform(delete("/api/v1/repairs/{id}", 1L))
                .andExpect(status().isOk()) // Checking if the response is 200 OK
                .andExpect(content().string("false")); // Checking if the content is "false"


        verify(repairService).deleteRepair(1L);
    }




}
