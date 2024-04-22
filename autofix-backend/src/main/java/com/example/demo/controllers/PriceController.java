package com.example.demo.controllers;

import com.example.demo.entities.PriceEntity;
import com.example.demo.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@CrossOrigin("*")
public class PriceController {
    @Autowired
    PriceService priceService;

    @GetMapping("/")
    public ResponseEntity<List<PriceEntity>> getPrices(){
        List<PriceEntity> prices = priceService.getPrices();
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/engine_{engine}")
    public ResponseEntity<List<PriceEntity>> getPricesByEngine(@PathVariable String engine){
        List<PriceEntity> prices = priceService.getPricesByEngine(engine);
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/type_{repair_type}")
    public ResponseEntity<List<PriceEntity>>  getPricesByRepairType(@PathVariable Integer repair_type){
        List<PriceEntity> prices = priceService.getPricesByRepairType(repair_type);
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/{engine}/{repair_type}")
    public ResponseEntity<Integer> getPriceByRepairTypeAndEngine(@PathVariable String engine,@PathVariable Integer repair_type){
        Integer price = priceService.getPriceByRepairTypeAndEngine(engine, repair_type);
        return ResponseEntity.ok(price);
    }

}
