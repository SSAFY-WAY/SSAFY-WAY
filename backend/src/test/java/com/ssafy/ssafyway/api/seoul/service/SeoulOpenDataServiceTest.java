package com.ssafy.ssafyway.api.seoul.service;

import com.ssafy.ssafyway.api.seoulopendata.service.SeoulOpenDataService;
import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeoulOpenDataServiceTest extends ServiceTest {
    @Autowired
    private HouseRepository houseDetailRepository;

    @Autowired
    private SeoulOpenDataService seoulOpenDataService;

    @DisplayName("API 호출을 통한 전세집 자료 DB 저장 Test")
    @Rollback(value = false)
    @Test
    void saveRentHouseByAPI() {
        assertDoesNotThrow(() -> seoulOpenDataService.fetchHouseData(10000));

        List<House> houses = houseDetailRepository.findAll();
        assertTrue(houses.size() >= 10);
    }

    @DisplayName("JSON 파일을 통한 전세집 자료 DB 저장 Test")
    @Rollback(value = false)
    @Test
    void saveRentHouseByFile() {
        assertDoesNotThrow(() -> seoulOpenDataService.loadHouseData());

        List<House> houses = houseDetailRepository.findAll();
        assertTrue(houses.size() >= 10);
    }
}
