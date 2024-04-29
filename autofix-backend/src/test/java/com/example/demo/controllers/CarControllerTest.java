package com.example.demo.controllers;


import com.example.demo.entities.CarEntity;
import com.example.demo.services.CarService;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void listCars_ShouldReturnCars() throws Exception {
        CarEntity car1 = new CarEntity("HHHH88",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);

        CarEntity car2 = new CarEntity("HHHH99",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);

        List<CarEntity> carList = new ArrayList<>(Arrays.asList(car1, car2));

        given(carService.getCars()).willReturn((ArrayList<CarEntity>) carList);

        mockMvc.perform(get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("HHHH88")))
                .andExpect(jsonPath("$[1].plate", is("HHHH99")));
    }

    @Test
    public void getCarByPlate_ShouldCar() throws Exception {

        CarEntity car = new CarEntity("HHHH99",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);

        given(carService.getCarByPlate("HHHH99")).willReturn(car);

        mockMvc.perform(get("/api/v1/cars/{plate}", "HHHH99"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate", is("HHHH99")));
    }

    @Test
    public void saveCar_ShouldReturnCar() throws Exception {
        CarEntity car = new CarEntity("HHHH99",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);

        String carJson = """
                {
                    "plate": "HHHH88",
                    "engine": "gas",
                    "brand": "Chevrolet",
                    "model": "n300",
                    "bodywork": "van",
                    "year": 2020,
                    "seats": 2,
                    "mileage": 47000
                }
                """;

        given(carService.saveCar(car)).willReturn(car);

        mockMvc.perform(post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCar() throws Exception {
        CarEntity car = new CarEntity("HHHH99",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);

        String carJson = """
                {
                    "plate": "HHHH88",
                    "engine": "gas",
                    "brand": "Chevrolet",
                    "model": "n300",
                    "bodywork": "van",
                    "year": 2020,
                    "seats": 2,
                    "mileage": 47000
                }
                """;

        given(carService.update(car)).willReturn(car);

        mockMvc.perform(put("/api/v1/cars/updt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCar_ShouldReturnNoContent() throws Exception {

        CarEntity car = new CarEntity("HHHH99",
                "diesel",
                "chevrolet",
                "n300",
                "van",
                2019,
                4,
                12345L);


        mockMvc.perform(delete("/api/v1/cars/{plate}", "HHHH99"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCar_ShouldReturnFalse_WhenAnErrorOccurs() throws Exception {

        doThrow(new RuntimeException("Error")).when(carService).deleteCar("HHHH88");


        mockMvc.perform(delete("/api/v1/cars/{plate}", "HHHH88"))
                .andExpect(status().isOk()) // Checking if the response is 200 OK
                .andExpect(content().string("false")); // Checking if the content is "false"


        verify(carService).deleteCar("HHHH88");
    }
}