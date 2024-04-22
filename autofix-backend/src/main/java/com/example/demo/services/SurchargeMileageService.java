package com.example.demo.services;

import com.example.demo.entities.SurchargeMileageEntity;
import com.example.demo.repositories.SurchargeMileageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurchargeMileageService {
    @Autowired
    SurchargeMileageRepository surchargeMileageRepository;

    public List<SurchargeMileageEntity> listSurchargesMileages(){
        return surchargeMileageRepository.findAll();
    }

    public float getSurchargeMileageByCategoryAndBodywork(String category, String bodywork){
        return surchargeMileageRepository.getSurchargeMileageByCategoryAndBodywork(category, bodywork);
    }
}
