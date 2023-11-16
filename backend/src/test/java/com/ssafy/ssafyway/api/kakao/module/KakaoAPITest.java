package com.ssafy.ssafyway.api.kakao.module;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.housedetail.fixture.RentRowFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoAPITest extends ServiceTest {
    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();

    @Autowired
    private KakaoMapFetchAPI kakaoMapFetchAPI;

    @DisplayName("kakaoAPI")
    @Test
    void kakaoAPIConnectTest() {
        Documents response = kakaoMapFetchAPI.kakaoAddressSearch(
                row.getSggNm(), row.getBjdongNm(), row.getBobn(), row.getBubn());

        assertThat(response).isNotNull();
    }
}
