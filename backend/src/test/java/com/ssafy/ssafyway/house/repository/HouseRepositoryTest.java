package com.ssafy.ssafyway.house.repository;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.global.config.RepositoryTest;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.fixture.HouseFixture;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class HouseRepositoryTest extends RepositoryTest {
    @Autowired
    private HouseRepository houseDetailRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RegionRepository regionRepository;
    private Building building;
    private House house;

    @BeforeEach
    void setup() {
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        building = buildingRepository.save(BuildingFixture.JU_GONG4.toHouseGeo(region));
    }

    @DisplayName("house를 저장한다.")
    @Test
    void saveSuccess() {
        /* Given */
        house = HouseFixture.JU_GONG4_1.toHouseDetail(building);

        /* When */
        House savedHouse = houseDetailRepository.save(house);

        /* Then */
        assertThat(savedHouse).isEqualTo(house);
    }

    @DisplayName("house를 식별자로 조회한다.")
    @Test
    void findByIdSuccess() {
        /* Given */
        house = HouseFixture.JU_GONG4_1.toHouseDetail(building);
        House savedHouse = houseDetailRepository.save(house);

        /* When */
        Optional<House> optionalHouseDetail = houseDetailRepository.findById(savedHouse.getId());

        /* Then */
        assertThat(optionalHouseDetail).isPresent();
    }

}
