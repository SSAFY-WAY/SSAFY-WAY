package com.ssafy.ssafyway.api.seoul.service;

import com.ssafy.ssafyway.api.seoulopendata.service.SeoulOpenDataService;
import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.housedetail.domain.HouseDetailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeoulOpenDataServiceTest extends ServiceTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;

    @Autowired
    private SeoulOpenDataService seoulOpenDataService;

    @DisplayName("API 호출을 통한 전세집 자료 DB 저장 Test")
    @Rollback(value = false)
    @Test
    void saveRentHouseToDB() {
        assertDoesNotThrow(() -> seoulOpenDataService.fetchHouseData(10000));

        List<HouseDetail> houseDetails = houseDetailRepository.findAll();
        assertTrue(houseDetails.size() >= 10);
    }

    @DisplayName("파일을 통한 전세집 자료 DB 저장 Test (File)")
    @Rollback(value = false)
    @Test
    void saveRentHouseToDBFile() {
        assertDoesNotThrow(() -> seoulOpenDataService.loadHouseData());

        List<HouseDetail> houseDetails = houseDetailRepository.findAll();
        assertTrue(houseDetails.size() >= 10);
    }
}
