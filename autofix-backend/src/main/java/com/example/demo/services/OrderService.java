package com.example.demo.services;

import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final RepairService repairService;
    private final DiscountBonusService discountBonusService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(RepairService repairService, DiscountBonusService discountBonusService, RepairRepository repairRepository, OrderRepository orderRepository) {
        this.repairService = repairService;
        this.discountBonusService = discountBonusService;
        this.orderRepository = orderRepository;
    }


    public float getBonusByRepairCode(String repairCode) {
        return orderRepository.getBonusByRepairCode(repairCode);
    }

    public OrderEntity closeRepairOrder(Long id){
        RepairEntity repair = repairService.getById(id);
        float disc_bonus = 0.0f;
        System.out.println("BBBBBBBBBBB brand: " + repair.getBrand());
        if (discountBonusService.getStockByBrand(repair.getBrand()) <= 0) {
            System.out.println("No hay stock de descuento por marca");
            //repair.setDiscBonus(0.0f);
            //disc_bonus = 0.0f;
        } else {
            disc_bonus = discountBonusService.getBonusByBrand(repair.getBrand());
            if (disc_bonus != 0) {
                discountBonusService.decreaseStockByBrand(repair.getBrand());
            }
            //repair.setDiscBonus(disc_bonus); // not a percentage
        }

        // Obtener suma total by repaircode
        float totalAmount = repairService.sumTotalAmountByRepairCode(repair.getRepairCode())-disc_bonus;

        OrderEntity order = orderRepository.save(new OrderEntity(repair.getRepairCode(), repair.getPlate(), repair.getEngine(),
                repair.getBodywork(), disc_bonus, totalAmount));
        return orderRepository.save(order);
    }

}
