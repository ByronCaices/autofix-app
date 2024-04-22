package com.example.demo.repositories;

import com.example.demo.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity,Long> {
    public RepairEntity findById(long id);
    public List<RepairEntity> findByPlate(String plate);
    public List<RepairEntity> findByBodywork(String bodywork);
    public List<RepairEntity> findByRepairType(Integer repairType);
    public List<RepairEntity> findByEngine(String engine);
    public Integer countByPlate(String plate);

    @Query(value="SELECT COUNT(*)\n" +
            "FROM repairs\n" +
            "WHERE\n" +
            "    finish_date >= CURRENT_DATE - INTERVAL '12 months'\n" +
            "    AND plate = :plate", nativeQuery = true)
    public Integer countByPlateLastYear(@Param("plate")String plate);

    @Query(value = "SELECT repair_type,\n" +
            "       SUM(total_amount) AS suma_amount_repair_type,\n" +
            "       COUNT(*) AS number_repairs,\n" +
            "       bodywork\n" +
            "FROM repairs\n" +
            "GROUP BY repair_type, bodywork\n" +
            "ORDER BY repair_type, bodywork", nativeQuery = true)
    List<Object[]> getRepairTypeAmountsByBodywork();

    @Query(value = "SELECT repair_type,\n" +
            "       SUM(total_amount) AS suma_amount_repair_type,\n" +
            "       COUNT(*) AS number_repairs,\n" +
            "       engine\n" +
            "FROM repairs\n" +
            "GROUP BY repair_type, engine\n" +
            "ORDER BY SUM(total_amount) DESC, repair_type, engine", nativeQuery = true)
    List<Object[]> getRepairTypeAmountsByEngine();

}
