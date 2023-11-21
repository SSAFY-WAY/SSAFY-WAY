package com.ssafy.ssafyway.wishlist.repository;

import com.ssafy.ssafyway.global.config.RepositoryTest;
import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.fixture.HouseFixture;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import com.ssafy.ssafyway.wishlist.domain.Wishlist;
import com.ssafy.ssafyway.wishlist.domain.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WishlistRepositoryTest extends RepositoryTest {
    @Autowired
    private HouseRepository houseDetailRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    private Member member;
    private House house;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        Building building = buildingRepository.save(BuildingFixture.JU_GONG4.toHouseGeo(region));
        house = houseDetailRepository.save(HouseFixture.GRAND_TOWER_3.toHouseDetail(building));
    }


    @DisplayName("existWishlist 테스트")
    @Nested
    class existWishlistTest{
        @DisplayName("존재할 때")
        @Test
        public void existWishlistTestWhenExisted() {
            /* Given */
            int memberId = member.getId();
            int houseDetailId = house.getId();
            wishlistRepository.save(Wishlist.builder().house(house).member(member).build());

            /* When */
            boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseId(memberId, houseDetailId);

            /* Then */
            assertTrue(isWishlist);
        }

        @DisplayName("존재하지 않을 때")
        @Test
        public void existWishlistTestWhenNotExisted() {
            /* Given */
            int memberId = member.getId();
            int houseDetailId = house.getId()+1;
            wishlistRepository.save(Wishlist.builder().house(house).member(member).build());

            /* When */
            boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseId(memberId, houseDetailId);

            /* Then */
            assertFalse(isWishlist);
        }
    }

}
