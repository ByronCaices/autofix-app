package com.example.demo.controllers;


import com.example.demo.entities.RepairEntity;
import com.example.demo.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairs")
@CrossOrigin("*")
public class RepairController {
    @Autowired
    RepairService repairService;

    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> listRepairs() {
        List<RepairEntity> repairs = repairService.getRepairs();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/type_{repair_type}")
    public ResponseEntity<List<RepairEntity>> getRepairsByRepairType(@PathVariable Integer repair_type) {
        List<RepairEntity> repairs = repairService.getRepairsByRepairType(repair_type);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/plate_{plate}")
    public ResponseEntity<List<RepairEntity>> getRepairsByPlate(@PathVariable String plate) {
        List<RepairEntity> repairs = repairService.getRepairsByPlate(plate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/bodywork_{bodywork}")
    public ResponseEntity<List<RepairEntity>> getRepairsByBodywork(@PathVariable String bodywork) {
        List<RepairEntity> repairs = repairService.getRepairsByBodywork(bodywork);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/count_{plate}")
    public ResponseEntity<Integer> countRepairsByPlate(@PathVariable String plate) {
        Integer count = repairService.countByPlate(plate);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/repairTypeAmounts")
    public ResponseEntity<List<Object[]>> getRepairTypeAmounts() {
        List<Object[]> repairTypeAmounts = repairService.getRepairTypeAmounts();
        return ResponseEntity.ok(repairTypeAmounts);
    }

    @GetMapping("/repairTypeAmountsByEngine")
    public ResponseEntity<List<Object[]>> getRepairTypeAmountsByEngine() {
        List<Object[]> repairTypeAmounts = repairService.getRepairTypeAmountsByEngine();
        return ResponseEntity.ok(repairTypeAmounts);
    }

    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair){
        RepairEntity repairUpdated = repairService.saveRepair(repair); //Agrega los datos faltantes al repair sobre precios y descuentos
        return ResponseEntity.ok(repairUpdated);
    }

    @PutMapping("/calcFinalPrice/{id}")
    public ResponseEntity<RepairEntity> updateRepair(@PathVariable Long id){
        RepairEntity repairUpdated = repairService.addSurchPickupDelay(id);
        return ResponseEntity.ok(repairUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Long id){
        try {
            repairService.deleteRepair(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/repairCode_{repair_code}")
    public ResponseEntity<List<RepairEntity>> getRepairsByRepairCode(@PathVariable String repair_code) {
        List<RepairEntity> repairs = repairService.getRepairsByRepairCode(repair_code);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/totalAmount_{repair_code}")
    public ResponseEntity<Float> sumTotalAmountByRepairCode(@PathVariable String repair_code) {
        float totalAmount = repairService.sumTotalAmountByRepairCode(repair_code);
        return ResponseEntity.ok(totalAmount);
    }

}
