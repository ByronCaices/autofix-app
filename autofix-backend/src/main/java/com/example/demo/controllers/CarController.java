package com.example.demo.controllers;
import com.example.demo.entities.CarEntity;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin("*")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> listCars() {
        List<CarEntity> cars = carService.getCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<CarEntity> getCarByPlate(@PathVariable String plate) {
        CarEntity car = carService.getCarByPlate(plate);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/brand/{this_brand}")
    public ResponseEntity<ArrayList<CarEntity>> getCarsByBrand(@PathVariable String this_brand) {
        ArrayList<CarEntity> cars = carService.findByBrand(this_brand);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand")
    public ResponseEntity<ArrayList<CarEntity>> getCarsByBrandParam(@RequestParam(name="brand") String this_brand) {
        ArrayList<CarEntity> cars = carService.findByBrand(this_brand);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bodywork")
    public ResponseEntity<ArrayList<CarEntity>> getCarsByBodywork(@RequestParam(name="bodywork") String this_brand) {
        ArrayList<CarEntity> cars = carService.findByBrand(this_brand);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/")
    public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity car){
        CarEntity carSaved = carService.saveCar(car);
        return ResponseEntity.ok(carSaved);
    }

    @PutMapping("/updateMileage/{plate}/{mileage}")
    public ResponseEntity<CarEntity> updateMileage(@PathVariable String plate, @PathVariable Long mileage){
        CarEntity car = carService.updateMileage(plate, mileage);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{plate}")
    public ResponseEntity<Boolean> deleteCarByPlate(@PathVariable String plate) throws Exception {
        var isDeleted = carService.deleteCar(plate);
        return ResponseEntity.noContent().build();
    }


}
