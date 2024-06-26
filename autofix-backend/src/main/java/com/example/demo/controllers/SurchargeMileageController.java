package com.example.demo.controllers;

import com.example.demo.entities.SurchargeMileageEntity;
import com.example.demo.services.SurchargeMileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surchmileages")
@CrossOrigin("*")
public class SurchargeMileageController {
    @Autowired
    SurchargeMileageService surchargeMileageService;

    @GetMapping("/")
    public ResponseEntity<List<SurchargeMileageEntity>> listSurchargesMileages() {
        List<SurchargeMileageEntity> surchargesMileages = surchargeMileageService.listSurchargesMileages();
        return ResponseEntity.ok(surchargesMileages);
    }

    @GetMapping("/{category}/{bodywork}")
    public ResponseEntity<Float> getSurchargeMileageByCategoryAndBodywork(@PathVariable String category,@PathVariable String bodywork) {
        Float surcharge = surchargeMileageService.getSurchargeMileageByCategoryAndBodywork(category, bodywork);
        return ResponseEntity.ok(surcharge);
    }
}
