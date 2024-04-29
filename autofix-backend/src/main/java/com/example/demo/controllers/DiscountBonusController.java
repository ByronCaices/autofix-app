package com.example.demo.controllers;

import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.services.DiscountBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discbonus")
@CrossOrigin("*")
public class DiscountBonusController {
    @Autowired
    DiscountBonusService discountBonusService;

    @GetMapping("/")
    public ResponseEntity<List<DiscountBonusEntity>> getDiscountBonus() {
        List<DiscountBonusEntity> discountBonus = discountBonusService.getDiscountBonus();
        return ResponseEntity.ok(discountBonus);
    }

    @GetMapping("/brand_{brand}")
    public ResponseEntity<Integer> getBonusByBrand(@PathVariable String brand) {
        Integer bonus = discountBonusService.getBonusByBrand(brand);
        return ResponseEntity.ok(bonus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountBonusEntity> getDiscountBonusById(@PathVariable Long id) {
        DiscountBonusEntity discountBonus = discountBonusService.getDiscountBonusById(id);
        return ResponseEntity.ok(discountBonus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountBonusByBrand(@PathVariable Long id) {
        discountBonusService.deleteDiscountBonusByBrand(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<DiscountBonusEntity> saveDiscountBonus(@RequestBody DiscountBonusEntity discountBonus){
        DiscountBonusEntity discountBonusSaved = discountBonusService.saveDiscountBonus(discountBonus);
        return ResponseEntity.ok(discountBonusSaved);
    }

    @PutMapping("/")
    public ResponseEntity<DiscountBonusEntity> updateDiscountBonus(@RequestBody DiscountBonusEntity discountBonus) {
        DiscountBonusEntity discountBonusUpdated = discountBonusService.saveDiscountBonus(discountBonus);
        return ResponseEntity.ok(discountBonusUpdated);
    }

    @PutMapping("/decreaseStock/{brand}")
    public ResponseEntity<Void> decreaseStockByBrand(@PathVariable String brand) {
        discountBonusService.decreaseStockByBrand(brand);
        return ResponseEntity.ok().build();
    }

}
