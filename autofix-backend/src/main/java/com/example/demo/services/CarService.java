package com.example.demo.services;

import com.example.demo.entities.CarEntity;
import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public ArrayList<CarEntity> getCars(){ return (ArrayList<CarEntity>) carRepository.findAll(); }

    public CarEntity saveCar(CarEntity car){ return carRepository.save(car); }

    public CarEntity updateMileage(String plate, Long mileage){
        CarEntity car = carRepository.findByPlate(plate);
        car.setMileage(mileage);
        return carRepository.save(car);
    }

    public CarEntity update(CarEntity car){
        return carRepository.save(car);
    }

    public boolean deleteCar(String plate) throws Exception {
        try{
            carRepository.deleteCarByPlate(plate);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public CarEntity getCarByPlate(String plate){ return carRepository.findByPlate(plate); }

    public ArrayList<CarEntity> findByBrand(String brand){ return (ArrayList<CarEntity>) carRepository.findByBrand(brand); }

    public ArrayList<CarEntity> findByBodywork(String bodywork){ return (ArrayList<CarEntity>) carRepository.findByBodywork(bodywork); }

    public CarEntity updateCar(CarEntity car) { return carRepository.save(car); }
}
