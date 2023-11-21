package com.ssafy.ssafyway.house.service;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.global.config.MockitoTest;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.fixture.HouseFixture;
import com.ssafy.ssafyway.house.fixture.RentRowFixture;
import com.ssafy.ssafyway.house.mapper.HouseMapper;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class HouseServiceMockTest extends MockitoTest {

    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    @InjectMocks
    private HouseService houseService;
    @Mock
    private HouseRepository houseDetailRepository;
    @Spy
    private HouseMapper houseMapper;

    @DisplayName("houseDetail 을 저장한다.")
    @Test
    void houseDetailSaveTestSuccess() {
        // given
        Region region = RegionFixture.REGION_ONE.toRegion(row);
        Building building = BuildingFixture.CREATE_HOUSE_GEO.toHouseGeo(region);
        House house = HouseFixture.CREATE_HOUSE_DETAIL.toHouseDetail(building, row);
        given(houseDetailRepository.save(any())).willReturn(house);

        // when,then
        assertDoesNotThrow(() -> houseService.saveHouseDetail(row, building));
    }
}
