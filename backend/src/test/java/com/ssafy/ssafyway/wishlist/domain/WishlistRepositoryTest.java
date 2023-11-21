package com.ssafy.ssafyway.wishlist.domain;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.global.config.RepositoryTest;
import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.domain.HouseRepository;
import com.ssafy.ssafyway.house.fixture.HouseFixture;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.fixture.BuildingFixture;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.fixture.RegionFixture;
import com.ssafy.ssafyway.wishlist.vo.WishlistVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WishlistRepositoryTest extends RepositoryTest {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private HouseRepository houseDetailRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RegionRepository regionRepository;
    private House house;
    private Member member;


    @BeforeEach
    void setUp() {
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        Building building = buildingRepository.save(BuildingFixture.SAM_DO.toHouseGeo(region));
        house = houseDetailRepository.save(HouseFixture.GRAND_TOWER_3.toHouseDetail(building));
        member = saveMember(MemberFixture.JEONGUK);
    }

    @DisplayName("memberId별 wishList찾기")
    @Test
    void findWishListByMemberId() {
        /* Given */
        wishlistRepository.save(Wishlist.builder()
                .member(member)
                .house(house)
                .build());

        /* When */
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(member.getId());

        /* Then */
        assertEquals(1, wishlistVOList.size());
    }

    @DisplayName("wishlist 삭제")
    @Nested
    class deleteWishList {
        @DisplayName("wishlsit의 memberid와 요청자의 member id가 같을 때")
        @Test
        void deleteWishListWhenSuccess() {
            /* Given */
            Wishlist wishlist = wishlistRepository.save(Wishlist.builder()
                    .member(member)
                    .house(house)
                    .build());

            /* When */
            Wishlist findWishList =
                    wishlistRepository.findByIdAndMemberId(wishlist.getId(), member.getId()).orElseThrow();
            findWishList.removeRelated();
            wishlistRepository.delete(findWishList);

            /* Then */
            List<Wishlist> afterWishList = wishlistRepository.findAll();
            assertEquals(0, afterWishList.size());
        }

        @DisplayName("wishlsit의 memberid와 요청자의 member id가 다를 때")
        @Test
        void deleteWishListWhenFail() {
            /* Given */
            int wishlistId = wishlistRepository.save(Wishlist.builder()
                    .member(member)
                    .house(house)
                    .build()).getId();

            /* When, Then */
            assertThat(wishlistRepository.findByIdAndMemberId(wishlistId, 200)).isEmpty();
        }
    }
}