package com.ssafy.ssafyway.housedetail.service;

import com.ssafy.ssafyway.auth.vo.AuthMember;
import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.ssafyway.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.housedetail.domain.HouseDetailRepository;
import com.ssafy.ssafyway.housedetail.exception.HouseDetailErrorCode;
import com.ssafy.ssafyway.housedetail.exception.HouseDetailException;
import com.ssafy.ssafyway.housedetail.fixture.HouseDetailFixture;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;
import com.ssafy.ssafyway.housegeo.domain.HouseGeoRepository;
import com.ssafy.ssafyway.housegeo.fixture.HouseGeoFixture;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HouseDetailServiceTest extends ServiceTest {

    @Autowired
    private HouseDetailService houseDetailService;
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;

    private Member member;
    private HouseGeo geo;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        geo = houseGeoRepository.save(HouseGeoFixture.GRAND_TOWER.toHouseGeo(region));
        houseDetail = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(geo));
    }

    @DisplayName("최근 본 집 목록을 조회한다.")
    @Test
    void findByRecentViewTestSuccess() {
        /* Given */
        AuthMember authMember = new AuthMember(member.getId());
        int id2 = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(geo)).getId();
        int id3 = houseDetailRepository.save(HouseDetailFixture.JU_GONG4_1.toHouseDetail(geo)).getId();

        /* When */
        HouseDetailRecentViewResponse response = houseDetailService.findByRecentView(
                authMember, new HouseDetailRecentViewRequest(List.of(id2, id3)));

        /* Then */
        assertThat(response.getHouseDetailList()).hasSize(2);
    }

    @DisplayName("HouseDetail을 Id로 조회한다.")
    @Nested
    class FindByIdTest {
        @DisplayName("조회에 성공한다.")
        @Test
        void success() {
            /* Given */
            int id = houseDetail.getId();

            /* When */
            HouseDetail findDetail = houseDetailService.findById(id);

            /* Then */
            assertThat(findDetail).isEqualTo(houseDetail);
        }

        @DisplayName("조회에 실패한다.")
        @Test
        void fail() {
            /* Given */
            int id = Integer.MAX_VALUE;

            /* When */
            /* Then */
            assertThatThrownBy(() -> houseDetailService.findById(id))
                    .isInstanceOf(HouseDetailException.class)
                    .hasMessageContaining(
                            HouseDetailErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED.getMessage());
        }
    }
}