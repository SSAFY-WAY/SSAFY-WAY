package com.ssafy.ssafyway.house.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByHouseCond;
import com.ssafy.ssafyway.house.domain.House;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.ssafy.ssafyway.building.domain.QBuilding.building;
import static com.ssafy.ssafyway.region.domain.QRegion.region;
import static com.ssafy.ssafyway.house.domain.QHouse.house;

public class HouseRepositoryImpl implements HouseRepositoryCustom {
    private final JPAQueryFactory query;

    public HouseRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Optional<House> findByDataCond(ExistByHouseCond cond) {
        House findHouse = query.selectFrom(house)
                .join(house.building, building)
                .join(building.region, region)
                .where(region.districtCode.eq(cond.getDistrictCode())
                        .and(region.legalDongCode.eq(cond.getLegalDongCode()))
                        .and(building.bobn.eq(cond.getBobn()))
                        .and(building.bubn.eq(cond.getBubn()))
                        .and(house.floor.eq(cond.getFloor()))
                        .and(house.area.eq(cond.getArea()))
                        .and(house.price.eq(cond.getPrice())))
                .fetchOne();
        return Optional.ofNullable(findHouse);
    }
}
