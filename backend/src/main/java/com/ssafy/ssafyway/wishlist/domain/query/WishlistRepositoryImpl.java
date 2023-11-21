package com.ssafy.ssafyway.wishlist.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafyway.wishlist.vo.QWishlistVO;
import com.ssafy.ssafyway.wishlist.vo.WishlistVO;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;


import static com.ssafy.ssafyway.building.domain.QBuilding.building;
import static com.ssafy.ssafyway.wishlist.domain.QWishlist.wishlist;
import static com.ssafy.ssafyway.member.domain.QMember.member;
import static com.ssafy.ssafyway.region.domain.QRegion.region;
import static com.ssafy.ssafyway.house.domain.QHouse.house;

@Slf4j
public class WishlistRepositoryImpl implements WishlistRepositoryCustom {
    private final JPAQueryFactory query;

    public WishlistRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<WishlistVO> findByMemberId(int memberId) {
        return query.select(new QWishlistVO(
                                house.id,
                                region.districtName,
                                region.legalDongName,
                                building.buildingName,
                                building.latitude,
                                building.longitude,
                                house.area,
                                house.price,
                                building.constructionYear,
                                house.floor,
                                building.buildingType
                        )
                )
                .from(wishlist)
                .join(wishlist.member, member)
                .join(wishlist.house, house)
                .join(house.building, building)
                .join(building.region, region)
                .where(wishlist.member.id.eq(memberId))
                .fetch();
    }
}