package com.example.demo.services;

import com.example.demo.entities.DiscountBonusEntity;
import com.example.demo.repositories.DiscountBonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountBonusService {
    @Autowired
    DiscountBonusRepository discountBonusRepository;

    public List<DiscountBonusEntity> getDiscountBonus(){
        return discountBonusRepository.findAll();
    }

    public DiscountBonusEntity getByBrand(String brand) {
        return discountBonusRepository.findByBrand(brand);
    }

    public int getBonusByBrand(String brand) {
        Integer bonus = discountBonusRepository.findBonusByBrand(brand);
        return Optional.ofNullable(bonus).orElse(0);
    }

    public void decreaseStockByBrand(String brand) {
        DiscountBonusEntity discountBonus = this.getByBrand(brand);
        discountBonus.setStock(discountBonus.getStock() - 1);
        discountBonusRepository.save(discountBonus);
    }



}
















