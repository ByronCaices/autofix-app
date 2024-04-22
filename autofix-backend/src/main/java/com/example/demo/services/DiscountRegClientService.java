package com.example.demo.services;

import com.example.demo.entities.DiscountRegClientEntity;
import com.example.demo.repositories.DiscountRegClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountRegClientService {
    @Autowired
    DiscountRegClientRepository discountRegClientRepository;

    public List<DiscountRegClientEntity> getDiscountRegClients(){
        return discountRegClientRepository.findAll();
    }

    public Float getDiscountByCategoryAndEngine(String category, String engine){
        return discountRegClientRepository.findDiscountRCByCategoryAndEngine(category, engine);
    }

}
