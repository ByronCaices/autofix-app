package com.example.demo.services;

import com.example.demo.entities.CarEntity;
import com.example.demo.entities.RepairEntity;
import com.example.demo.repositories.RepairRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(RepairService.class)
public class RepairServiceTest {

    @Autowired
    RepairService repairService;

    @MockBean
    RepairRepository repairsRepository;

    @MockBean
    PriceService priceService;

    @MockBean
    DiscountRegClientService discountRegClientService;

    @MockBean
    DiscountBonusService discountBonusService;

    @MockBean
    SurchargeCarAgeService surchargeCarAgeService;

    @MockBean
    CarService carService;

    @Test
    void whenGetRepairs_thenCorrect() {
        //Given
        RepairEntity repair1 = new RepairEntity();
        RepairEntity repair2 = new RepairEntity();
        RepairEntity repair3 = new RepairEntity();
        ArrayList<RepairEntity> repairs = new ArrayList<RepairEntity>();
        repairs.add(repair1);
        repairs.add(repair2);
        repairs.add(repair3);

        //When
        when(repairsRepository.findAllOrderByCheckinDate()).thenReturn(repairs);
        List<RepairEntity> repairsList = repairService.getRepairs();

        //Then
        assertNotNull(repairsList);
        assertThat(repairsList.size()).isEqualTo(3);
    }

    @Test
    void whenUpdateRepair_thenCorrect() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        //Given
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        //When
        when(repairsRepository.save(repair)).thenReturn(repair);
        RepairEntity updatedRepair = repairService.updateRepair(repair);

        //Then
        assertNotNull(updatedRepair);
        assertThat(updatedRepair.getId()).isEqualTo(1L);
        assertThat(updatedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(updatedRepair.getBrand()).isEqualTo("toyota");
    }

    @Test
    void whenGetById_thenCorrect() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        //Given
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        //When
        when(repairsRepository.findById(1L)).thenReturn(java.util.Optional.of(repair));
        RepairEntity foundRepair = repairService.getById(1L);

        //Then
        assertNotNull(foundRepair);
        assertThat(foundRepair.getId()).isEqualTo(1L);
        assertThat(foundRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(foundRepair.getBrand()).isEqualTo("toyota");
    }

    @Test
    void whenGetRepairTypeAmounts_thenCorrect() {
        //Given
        Object[] repair1 = new Object[]{1,123456,2,"sedan"};
        Object[] repair2 = new Object[]{1,123456,2,"suv"};
        List<Object[]> repairs = new ArrayList<Object[]>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairsRepository.getRepairTypeAmountsByBodywork()).thenReturn(repairs);
        List<Object[]> repairsList = repairService.getRepairTypeAmounts();

        //Then
        assertNotNull(repairsList);
        assertThat(repairsList.size()).isEqualTo(2);
    }

    @Test
    void whenGetRepairTypeAmountsByEngine_thenCorrect() {
        //Given
        Object[] repair1 = new Object[]{1,123456,2,"gas"};
        Object[] repair2 = new Object[]{1,123456,2,"electric"};
        List<Object[]> repairs = new ArrayList<Object[]>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairsRepository.getRepairTypeAmountsByEngine()).thenReturn(repairs);
        List<Object[]> repairsList = repairService.getRepairTypeAmountsByEngine();

        //Then
        assertNotNull(repairsList);
        assertThat(repairsList.size()).isEqualTo(2);
    }

    @Test
    void whenGetAverageRepairTimeByBrand_thenCorrect() {
        //Given
        Object[] repair1 = new Object[]{"toyota",25.7};
        Object[] repair2 = new Object[]{"ford",12.3};
        List<Object[]> repairs = new ArrayList<Object[]>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairsRepository.getAverageRepairTimeByBrand()).thenReturn(repairs);
        List<Object[]> repairsList = repairService.getAverageRepairTimeByBrand();

        //Then
        assertNotNull(repairsList);
        assertThat(repairsList.size()).isEqualTo(2);
    }

    @Test
    void whenSaveRepair_thenCorrect() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2024-04-15");
        //Date date2 = formatter.parse("2021-01-02");
        //Date date3 = formatter.parse("2021-01-03");
        //Given
        CarEntity car = new CarEntity("HHHH88","gas","chevrolet","n400","van",2020,4,2000L);
        when(carService.getCarByPlate("HHHH88")).thenReturn(car);
        when(priceService.getPriceByRepairTypeAndEngine("gas",1)).thenReturn(120000);
        when(repairsRepository.countByPlateLastYear("HHHH88")).thenReturn(1);
        when(discountRegClientService.getDiscountByCategoryAndEngine("A","gas")).thenReturn(18000f);
        when(discountBonusService.getBonusByBrand("chevrolet")).thenReturn(0);
        when(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("A","van")).thenReturn(0f);

        RepairEntity repairNew = new RepairEntity(1L,"",
                "HHHH88",
                "gas",
                "",
                "",
                1,
                2000L,
                date1,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        RepairEntity repairProcessed = new RepairEntity(1L,"HHHH8810000",
                "HHHH88",
                "gas",
                "van",
                "chevrolet",
                1,
                2000L,
                date1,
                null,
                null,
                120000,
                117096,
                18000,
                0,
                12000,
                0,
                8400,
                0,
                18696);

        //When
        when(repairsRepository.save(repairNew)).thenReturn(repairProcessed);
        RepairEntity savedRepair = repairService.saveRepair(repairNew);

        //Then
        assertNotNull(savedRepair);
        assertThat(savedRepair.getId()).isEqualTo(1L);
        assertThat(savedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(savedRepair.getBrand()).isEqualTo("chevrolet");
        assertThat(savedRepair.getRepairCode()).isEqualTo("HHHH8810000");
        assertThat(savedRepair.getRepairPrice()).isEqualTo(120000);
        assertThat(savedRepair.getDiscRegClient()).isEqualTo(18000);
        assertThat(savedRepair.getDiscBonus()).isEqualTo(0);
        assertThat(savedRepair.getDiscMonThu()).isEqualTo(12000);
        assertThat(savedRepair.getSurchCarage()).isEqualTo(0);
        assertThat(savedRepair.getSurchMileage()).isEqualTo(8400);
        assertThat(savedRepair.getSurchDelay()).isEqualTo(0);
        assertThat(savedRepair.getIva()).isEqualTo(18696);
    }

    @Test
    void whenSaveRepair_BthenCorrect() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2024-04-15");
        //Date date2 = formatter.parse("2021-01-02");
        //Date date3 = formatter.parse("2021-01-03");
        //Given
        CarEntity car = new CarEntity("HHHH88","gas","chevrolet","n400","van",2014,4,10000L);
        when(carService.getCarByPlate("HHHH88")).thenReturn(car);
        when(priceService.getPriceByRepairTypeAndEngine("gas",1)).thenReturn(120000);
        when(repairsRepository.countByPlateLastYear("HHHH88")).thenReturn(3);
        when(discountRegClientService.getDiscountByCategoryAndEngine("B","gas")).thenReturn(18000f);
        when(discountBonusService.getBonusByBrand("chevrolet")).thenReturn(0);
        when(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("B","van")).thenReturn(0f);

        RepairEntity repairNew = new RepairEntity(1L,"",
                "HHHH88",
                "gas",
                "",
                "",
                1,
                10000L,
                date1,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        RepairEntity repairProcessed = new RepairEntity(1L,"HHHH8810000",
                "HHHH88",
                "gas",
                "van",
                "chevrolet",
                1,
                10000L,
                date1,
                null,
                null,
                120000,
                117096,
                18000,
                0,
                12000,
                0,
                8400,
                0,
                18696);

        //When
        when(repairsRepository.save(repairNew)).thenReturn(repairProcessed);
        RepairEntity savedRepair = repairService.saveRepair(repairNew);

        //Then
        assertNotNull(savedRepair);
        assertThat(savedRepair.getId()).isEqualTo(1L);
        assertThat(savedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(savedRepair.getBrand()).isEqualTo("chevrolet");
        assertThat(savedRepair.getRepairCode()).isEqualTo("HHHH8810000");
        assertThat(savedRepair.getRepairPrice()).isEqualTo(120000);
        assertThat(savedRepair.getDiscRegClient()).isEqualTo(18000);
        assertThat(savedRepair.getDiscBonus()).isEqualTo(0);
        assertThat(savedRepair.getDiscMonThu()).isEqualTo(12000);
        assertThat(savedRepair.getSurchCarage()).isEqualTo(0);
        assertThat(savedRepair.getSurchMileage()).isEqualTo(8400);
        assertThat(savedRepair.getSurchDelay()).isEqualTo(0);
        assertThat(savedRepair.getIva()).isEqualTo(18696);
    }

    @Test
    void whenSaveRepair_CthenCorrect() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2024-04-15");
        //Date date2 = formatter.parse("2021-01-02");
        //Date date3 = formatter.parse("2021-01-03");
        //Given
        CarEntity car = new CarEntity("HHHH88","gas","chevrolet","n400","van",2010,4,13000L);
        when(carService.getCarByPlate("HHHH88")).thenReturn(car);
        when(priceService.getPriceByRepairTypeAndEngine("gas",1)).thenReturn(120000);
        when(repairsRepository.countByPlateLastYear("HHHH88")).thenReturn(6);
        when(discountRegClientService.getDiscountByCategoryAndEngine("C","gas")).thenReturn(18000f);
        when(discountBonusService.getBonusByBrand("chevrolet")).thenReturn(0);
        when(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("C","van")).thenReturn(0f);

        RepairEntity repairNew = new RepairEntity(1L,"",
                "HHHH88",
                "gas",
                "",
                "",
                1,
                13000L,
                date1,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        RepairEntity repairProcessed = new RepairEntity(1L,"HHHH8810000",
                "HHHH88",
                "gas",
                "van",
                "chevrolet",
                1,
                13000L,
                date1,
                null,
                null,
                120000,
                117096,
                18000,
                0,
                12000,
                0,
                8400,
                0,
                18696);

        //When
        when(repairsRepository.save(repairNew)).thenReturn(repairProcessed);
        RepairEntity savedRepair = repairService.saveRepair(repairNew);

        //Then
        assertNotNull(savedRepair);
        assertThat(savedRepair.getId()).isEqualTo(1L);
        assertThat(savedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(savedRepair.getBrand()).isEqualTo("chevrolet");
        assertThat(savedRepair.getRepairCode()).isEqualTo("HHHH8810000");
        assertThat(savedRepair.getRepairPrice()).isEqualTo(120000);
        assertThat(savedRepair.getDiscRegClient()).isEqualTo(18000);
        assertThat(savedRepair.getDiscBonus()).isEqualTo(0);
        assertThat(savedRepair.getDiscMonThu()).isEqualTo(12000);
        assertThat(savedRepair.getSurchCarage()).isEqualTo(0);
        assertThat(savedRepair.getSurchMileage()).isEqualTo(8400);
        assertThat(savedRepair.getSurchDelay()).isEqualTo(0);
        assertThat(savedRepair.getIva()).isEqualTo(18696);
    }

    @Test
    void whenSaveRepair_DthenCorrect() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2024-04-15");
        //Date date2 = formatter.parse("2021-01-02");
        //Date date3 = formatter.parse("2021-01-03");
        //Given
        CarEntity car = new CarEntity("HHHH88","gas","chevrolet","n400","van",2002,4,26000L);
        when(carService.getCarByPlate("HHHH88")).thenReturn(car);
        when(priceService.getPriceByRepairTypeAndEngine("gas",1)).thenReturn(120000);
        when(repairsRepository.countByPlateLastYear("HHHH88")).thenReturn(6);
        when(discountRegClientService.getDiscountByCategoryAndEngine("D","gas")).thenReturn(18000f);
        when(discountBonusService.getBonusByBrand("chevrolet")).thenReturn(0);
        when(surchargeCarAgeService.getSurchargeByCategoryAndBodywork("D","van")).thenReturn(0f);

        RepairEntity repairNew = new RepairEntity(1L,"",
                "HHHH88",
                "gas",
                "",
                "",
                1,
                26000L,
                date1,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        RepairEntity repairProcessed = new RepairEntity(1L,"HHHH8810000",
                "HHHH88",
                "gas",
                "van",
                "chevrolet",
                1,
                26000L,
                date1,
                null,
                null,
                120000,
                117096,
                18000,
                0,
                12000,
                0,
                8400,
                0,
                18696);

        //When
        when(repairsRepository.save(repairNew)).thenReturn(repairProcessed);
        RepairEntity savedRepair = repairService.saveRepair(repairNew);

        //Then
        assertNotNull(savedRepair);
        assertThat(savedRepair.getId()).isEqualTo(1L);
        assertThat(savedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(savedRepair.getBrand()).isEqualTo("chevrolet");
        assertThat(savedRepair.getRepairCode()).isEqualTo("HHHH8810000");
        assertThat(savedRepair.getRepairPrice()).isEqualTo(120000);
        assertThat(savedRepair.getDiscRegClient()).isEqualTo(18000);
        assertThat(savedRepair.getDiscBonus()).isEqualTo(0);
        assertThat(savedRepair.getDiscMonThu()).isEqualTo(12000);
        assertThat(savedRepair.getSurchCarage()).isEqualTo(0);
        assertThat(savedRepair.getSurchMileage()).isEqualTo(8400);
        assertThat(savedRepair.getSurchDelay()).isEqualTo(0);
        assertThat(savedRepair.getIva()).isEqualTo(18696);
    }

    @Test
    public void AddSurchPickupDelay_ShouldReturnUpdatedRepair() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse("2021-01-01");
        Date date2 = formatter.parse("2021-01-02");
        Date date3 = formatter.parse("2021-01-03");
        //Given
        RepairEntity repair = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                date1,
                date2,
                date3,
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        //When
        when(repairsRepository.findById(1L)).thenReturn(java.util.Optional.of(repair));
        when(repairsRepository.save(repair)).thenReturn(repair);
        RepairEntity updatedRepair = repairService.addSurchPickupDelay(1L);

        //Then
        assertNotNull(updatedRepair);
        assertThat(updatedRepair.getId()).isEqualTo(1L);
        assertThat(updatedRepair.getPlate()).isEqualTo("HHHH88");
        assertThat(updatedRepair.getBrand()).isEqualTo("toyota");
    }

    @Test
    public void getRepairsByRepairCode_ShouldReturnRepairs() {
        //Given
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                new Date(),
                new Date(),
                new Date(),
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
        RepairEntity repair2 = new RepairEntity(2L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                new Date(),
                new Date(),
                new Date(),
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
        List<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairsRepository.findByRepairCode("HHHH88ZZ")).thenReturn(repairs);
        List<RepairEntity> repairsList = repairService.getRepairsByRepairCode("HHHH88ZZ");

        //Then
        assertNotNull(repairsList);
        assertThat(repairsList.size()).isEqualTo(2);
    }

    @Test
    public void SumTotalAmountByRepairCode_ShouldReturnTotalAmount() {
        //Given
        RepairEntity repair1 = new RepairEntity(1L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                new Date(),
                new Date(),
                new Date(),
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
        RepairEntity repair2 = new RepairEntity(2L,"HHHH88ZZ",
                "HHHH88",
                "gas",
                "suv",
                "toyota",
                1,
                10000L,
                new Date(),
                new Date(),
                new Date(),
                1000,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0);
        List<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairsRepository.sumTotalAmountByRepairCode("HHHH88ZZ")).thenReturn(2000f);
        float totalAmount = repairService.sumTotalAmountByRepairCode("HHHH88ZZ");

        //Then
        assertThat(totalAmount).isEqualTo(2000f);
    }




}

