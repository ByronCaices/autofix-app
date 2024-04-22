package com.example.demo.repositories;

import com.example.demo.entities.CarEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String> {
    public CarEntity findByPlate(String plate);
    public List<CarEntity> findByBrand(String brand);
    public List<CarEntity> findByBodywork(String bodywork);
    public List<CarEntity> findCarEntitiesByEngine(String engine);
    public List<CarEntity> findCarEntitiesByYear(Integer year);

    @Query(value="DELETE FROM cars WHERE plate = :plate", nativeQuery = true)
    public void deleteByPlate(@Param("plate") String plate);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM cars WHERE plate = :plate", nativeQuery = true)
    int deleteCarByPlate(@Param("plate") String plate);


}
