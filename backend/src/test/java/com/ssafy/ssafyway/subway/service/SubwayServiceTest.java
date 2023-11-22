package com.ssafy.ssafyway.subway.service;

import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.subway.domain.SubwayRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubwayServiceTest extends ServiceTest {
    @Autowired
    SubwayService subwayService;

    @Autowired
    SubwayRepository subwayRepository;

    @DisplayName("지하철 역 자료 DB 저장 Test")
    @Rollback(value = false)
    @Test
    void saveSubwayToDB() {
        assertDoesNotThrow(() -> subwayService.loadSubwayData());
        assertTrue(subwayRepository.findAll().size() >= 10);
    }
}