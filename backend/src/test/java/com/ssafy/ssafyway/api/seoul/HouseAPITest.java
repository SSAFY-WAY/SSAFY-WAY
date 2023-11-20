package com.ssafy.ssafyway.api.seoul;

import com.ssafy.ssafyway.api.seoulopendata.data.dto.request.RentAPIRequest;
import com.ssafy.ssafyway.api.seoulopendata.data.dto.response.RentAPIResponse;
import com.ssafy.ssafyway.api.seoulopendata.service.SeoulOpenDataRentHouseFetchAPI;
import com.ssafy.ssafyway.global.config.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseAPITest extends ServiceTest {

    @Autowired
    private SeoulOpenDataRentHouseFetchAPI seoulOpenDataRentHouseAPI;

    @DisplayName("api 연동 test")
    @Test
    void connectAPI() {
        // given, when
        RentAPIResponse model = (RentAPIResponse)
                seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.toRequest(1, 4));

        // then
        assertEquals(5, model.getTbLnOpendataRentV().getRow().size());
    }

    @DisplayName("filtering 테스트")
    @Test
    void filteringRentHouseTest() {
        // given
        RentAPIResponse model = (RentAPIResponse)
                seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.toRequest(1, 4));

        // when
        // then
        Assertions.assertThatCode(() -> seoulOpenDataRentHouseAPI.filteringRentHouseByAPI(model))
                .doesNotThrowAnyException();
    }
}
