package com.ssafy.ssafyway.building.repository;

import com.ssafy.ssafyway.global.config.RepositoryTest;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BuildingRepositoryTest extends RepositoryTest {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RegionRepository regionRepository;
    private Building building;

    @BeforeEach
    void setUp() {
        Region region = regionRepository.save(RegionFixture.CREATE_REGION_BY_ROW.toRegion());
        building = buildingRepository.save(BuildingFixture.GRAND_TOWER.toHouseGeo(region));
    }

    @DisplayName("본번과 부번으로 Building 조회한다.")
    @Test
    void findByMainNumberAndSubNumberTest() {
        /* Given, When */
        Optional<Building> findHouseGeo = buildingRepository
                .findByBobnAndBubn(building.getBobn(), building.getBubn());

        /* Then */
        assertAll(
                () -> assertThat(findHouseGeo).isPresent(),
                () -> assertThat(findHouseGeo).contains(building)
        );
    }

}