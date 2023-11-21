package com.ssafy.ssafyway.house.service;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.global.config.ServiceTest;
import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.exception.HouseErrorCode;
import com.ssafy.ssafyway.house.exception.HouseException;
import com.ssafy.ssafyway.house.fixture.HouseFixture;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HouseServiceTest extends ServiceTest {

    @Autowired
    private HouseService houseService;
    @Autowired
    private HouseRepository houseDetailRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RegionRepository regionRepository;

    private Member member;
    private Building geo;
    private House house;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        geo = buildingRepository.save(BuildingFixture.GRAND_TOWER.toHouseGeo(region));
        house = houseDetailRepository.save(HouseFixture.GRAND_TOWER_3.toHouseDetail(geo));
    }

//    @DisplayName("최근 본 집 목록을 조회한다.")
//    @Test
//    void findByRecentViewTestSuccess() {
//        /* Given */
//        AuthMember authMember = new AuthMember(member.getId());
//        int id2 = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(geo)).getId();
//        int id3 = houseDetailRepository.save(HouseDetailFixture.JU_GONG4_1.toHouseDetail(geo)).getId();
//
//        /* When */
//        HouseDetailRecentViewResponse response = houseDetailService.findByRecentView(
//                authMember, new HouseDetailRecentViewRequest(List.of(id2, id3)));
//
//        /* Then */
//        assertThat(response.getHouseDetailList()).hasSize(2);
//    }

    @DisplayName("HouseDetail을 Id로 조회한다.")
    @Nested
    class FindByIdTest {
        @DisplayName("조회에 성공한다.")
        @Test
        void success() {
            /* Given */
            int id = house.getId();

            /* When */
            House findDetail = houseService.findById(id);

            /* Then */
            assertThat(findDetail).isEqualTo(house);
        }

        @DisplayName("조회에 실패한다.")
        @Test
        void fail() {
            /* Given */
            int id = Integer.MAX_VALUE;

            /* When */
            /* Then */
            assertThatThrownBy(() -> houseService.findById(id))
                    .isInstanceOf(HouseException.class)
                    .hasMessageContaining(
                            HouseErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED.getMessage());
        }
    }
}