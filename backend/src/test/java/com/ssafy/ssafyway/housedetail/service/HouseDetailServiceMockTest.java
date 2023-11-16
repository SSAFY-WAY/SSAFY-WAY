package com.ssafy.ssafyway.housedetail.service;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.global.config.MockitoTest;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.housedetail.domain.HouseDetailRepository;
import com.ssafy.ssafyway.housedetail.fixture.HouseDetailFixture;
import com.ssafy.ssafyway.housedetail.fixture.RentRowFixture;
import com.ssafy.ssafyway.housedetail.mapper.HouseDetailMapper;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;
import com.ssafy.ssafyway.housegeo.fixture.HouseGeoFixture;
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

class HouseDetailServiceMockTest extends MockitoTest {

    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    @InjectMocks
    private HouseDetailService houseDetailService;
    @Mock
    private HouseDetailRepository houseDetailRepository;
    @Spy
    private HouseDetailMapper houseDetailMapper;

    @DisplayName("houseDetail 을 저장한다.")
    @Test
    void houseDetailSaveTestSuccess() {
        // given
        Region region = RegionFixture.REGION_ONE.toRegion(row);
        HouseGeo houseGeo = HouseGeoFixture.CREATE_HOUSE_GEO.toHouseGeo(region);
        HouseDetail houseDetail = HouseDetailFixture.CREATE_HOUSE_DETAIL.toHouseDetail(houseGeo, row);
        given(houseDetailRepository.save(any())).willReturn(houseDetail);

        // when,then
        assertDoesNotThrow(() -> houseDetailService.saveHouseDetail(row, houseGeo));
    }
}
