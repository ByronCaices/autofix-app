package com.example.demo.services;

import com.example.demo.entities.SurchargeCarAgeEntity;
import com.example.demo.repositories.SurchargeCarAgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SurchargeCarAgeService {
    @Autowired
    SurchargeCarAgeRepository surchargeCarAgeRepository;

    public List<SurchargeCarAgeEntity> listSurchargesCarAges() {
        return surchargeCarAgeRepository.findAll();
    }

    public float getSurchargeByCategoryAndBodywork(String category, String bodywork) {
        return surchargeCarAgeRepository.getSurchargeByCategoryAndBodywork(category, bodywork);
    }



}
