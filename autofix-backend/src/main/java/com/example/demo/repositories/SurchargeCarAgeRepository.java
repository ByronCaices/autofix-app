package com.example.demo.repositories;

import com.example.demo.entities.SurchargeCarAgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurchargeCarAgeRepository extends JpaRepository<SurchargeCarAgeEntity, Long> {

    @Query(value = "SELECT surcharge FROM surcharge_carage WHERE category = :category AND bodywork = :bodywork", nativeQuery = true)
    public float getSurchargeByCategoryAndBodywork(@Param("category") String category, @Param("bodywork") String bodywork);
}
