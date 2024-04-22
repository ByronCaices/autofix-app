package com.example.demo.services;

import com.example.demo.entities.PriceEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public List<PriceEntity> getPrices(){
        return priceRepository.findAll();
    }

    public List<PriceEntity> getPricesByRepairType(Integer repairType){
        return priceRepository.findByRepairType(repairType);
    }

    public List<PriceEntity> getPricesByEngine(String engine){
        return priceRepository.findByEngine(engine);
    }

    public Integer getPriceByRepairTypeAndEngine(String engine,Integer repair_type){
        return priceRepository.findPriceEntityByEngineAndRepairType(engine, repair_type);
    }
}
