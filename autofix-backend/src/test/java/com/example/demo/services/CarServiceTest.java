package com.example.demo.services;
import com.example.demo.entities.CarEntity;
import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.entities.RepairEntity;
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

@WebMvcTest(CarService.class)
public class CarServiceTest {

    @Autowired
    CarService carService;

    @MockBean
    CarRepository carRepository;

    @Test
    void whenGetCars_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);

        ArrayList<CarEntity> carList = new ArrayList<>();
        carList.add(car);

        //When
        when(carRepository.findAllOrderByPlate()).thenReturn(carList); // Ajustar para que retorne un ArrayList directamente

        //Then
        ArrayList<CarEntity> result = carService.getCars();
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getBrand()).isEqualTo("Toyota");
        assertThat(result.get(0).getSeats()).isEqualTo(4);
        assertThat(result.get(0).getModel()).isEqualTo("Corolla");
        assertThat(result.get(0).getYear()).isEqualTo(2021);
    }

    @Test
    void whenSaveCar_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);

        //When
        when(carRepository.save(car)).thenReturn(car);

        //Then
        CarEntity result = carService.saveCar(car);
        assertNotNull(result);
        assertThat(result.getBrand()).isEqualTo("Toyota");
        assertThat(result.getSeats()).isEqualTo(4);
        assertThat(result.getModel()).isEqualTo("Corolla");
        assertThat(result.getYear()).isEqualTo(2021);
    }

    @Test
    void whenUpdateMileage_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);
        car.setMileage(1000L);

        //When
        when(carRepository.findByPlate("HHHH88")).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);

        //Then
        CarEntity result = carService.updateMileage("HHHH88", 2000L);
        assertNotNull(result);
        assertThat(result.getBrand()).isEqualTo("Toyota");
        assertThat(result.getSeats()).isEqualTo(4);
        assertThat(result.getModel()).isEqualTo("Corolla");
        assertThat(result.getYear()).isEqualTo(2021);
        assertThat(result.getMileage()).isEqualTo(2000L);
    }

    @Test
    void whenUpdate_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);
        car.setMileage(1000L);

        //When
        when(carRepository.save(car)).thenReturn(car);

        //Then
        CarEntity result = carService.update(car);
        assertNotNull(result);
        assertThat(result.getBrand()).isEqualTo("Toyota");
        assertThat(result.getSeats()).isEqualTo(4);
        assertThat(result.getModel()).isEqualTo("Corolla");
        assertThat(result.getYear()).isEqualTo(2021);
        assertThat(result.getMileage()).isEqualTo(1000L);
    }

    @Test
    void whenDeleteCar_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);
        car.setMileage(1000L);

        //When
        when(carRepository.deleteCarByPlate("HHHH88")).thenReturn(1);

        //Then
        boolean result = false;
        try {
            result = carService.deleteCar("HHHH88");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        assertThat(result).isTrue();
    }

    @Test
    public void whenDeleteCarCatchException_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);
        car.setMileage(1000L);

        //When
        when(carRepository.deleteCarByPlate("HHHH88")).thenThrow(new RuntimeException("Error"));

        //Then
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            carService.deleteCar("HHHH88");
        });
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenGetCarByPlate_thenCorrect() {
        //Given
        CarEntity car = new CarEntity();
        car.setPlate("HHHH88");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2021);
        car.setSeats(4);
        car.setMileage(1000L);

        //When
        when(carRepository.findByPlate("HHHH88")).thenReturn(car);

        //Then
        CarEntity result = carService.getCarByPlate("HHHH88");
        assertNotNull(result);
        assertThat(result.getBrand()).isEqualTo("Toyota");
        assertThat(result.getSeats()).isEqualTo(4);
        assertThat(result.getModel()).isEqualTo("Corolla");
        assertThat(result.getYear()).isEqualTo(2021);
        assertThat(result.getMileage()).isEqualTo(1000L);
    }
}
