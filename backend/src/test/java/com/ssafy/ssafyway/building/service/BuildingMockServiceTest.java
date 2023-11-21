package com.ssafy.ssafyway.building.service;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.global.config.MockitoTest;
import com.ssafy.ssafyway.house.fixture.RentRowFixture;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.building.mapper.BuildingMapper;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class BuildingMockServiceTest extends MockitoTest {
    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    private final Region region = RegionFixture.REGION_ONE.toRegion(row);
    private final Building building = BuildingFixture.CREATE_HOUSE_GEO.toHouseGeoByRow(row, region);
    @InjectMocks
    private BuildingService buildingService;
    @Mock
    private BuildingRepository buildingRepository;
    @Mock
    private KakaoMapFetchAPI kakaoMapFetchAPI;
    @Spy
    private BuildingMapper buildingMapper;

    @DisplayName("Building 조회")
    @Nested
    class getBuildingTest {
        @DisplayName("존재하는 좌표로 Building 조회한다.")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndExistedPositionTest() {
            // given
            given(buildingRepository.findByBobnAndBubn(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.of(building));

            // when,then
            assertDoesNotThrow(() -> buildingService.getBuilding(row, region));
        }

        @DisplayName("존재하는 좌표로 존재하지 않는 Building 조회시 예외를 던지지 않는다.")
        @Test
        void getHouseGeoWhenNotExistedHouseGeoAndExistedPositionTest() {
            // given
            given(buildingRepository.findByBobnAndBubn(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(buildingRepository.save(any()))
                    .willReturn(Building.builder()
                            .latitude(0)
                            .longitude(0)
                            .constructionYear(Integer.parseInt(row.getBuildYear()))
                            .buildingName(row.getBldgNm())
                            .bobn(Integer.parseInt(row.getBobn()) + 1)
                            .bubn(Integer.parseInt(row.getBubn()) + 1)
                            .region(region)
                            .build());
            Documents documents = new Documents();
            documents.setX("127.123123");
            documents.setY("45.231231234");
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any()))
                    .willReturn(documents);

            // when, then
            assertDoesNotThrow(() -> buildingService.getBuilding(row, region));
        }

        @DisplayName("존재하지 않는 좌표로 HouseGeo 조회시 예외를 던진다.")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndNotExistedPositionTest() {
            // given
            given(buildingRepository.findByBobnAndBubn(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any()))
                    .willThrow(IndexOutOfBoundsException.class);

            // when,then
            assertThrows(IndexOutOfBoundsException.class, () -> buildingService.getBuilding(row, region));
        }
    }
}
