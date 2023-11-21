package com.ssafy.ssafyway.building.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafyway.building.data.cond.BuildingFilterCond;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.global.domain.Points;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.ssafyway.building.domain.QBuilding.building;
import static com.ssafy.ssafyway.region.domain.QRegion.region;
import static com.ssafy.ssafyway.house.domain.QHouse.house;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    private final JPAQueryFactory query;

    public BuildingRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Building> findByFilterCond(BuildingFilterCond cond) {
        return query.selectFrom(building)
                .distinct()
                .join(building.region, region)
                .join(building.houseList, house).fetchJoin()
                .where(building.region.id.eq(cond.getRegionId()))
                .where(building.buildingType.in(cond.getTypes()))
                .where(house.area.between(cond.getMinArea(), cond.getMaxArea()))
                .where(house.price.between(cond.getMinPrice(), cond.getMaxPrice()))
                .fetch();
    }

    @Override
    public List<Building> findByBoundary(Points minPoints, Points maxPoints) {
        return query.selectFrom(building)
                .where(building.latitude
                        .between(minPoints.getLat(), maxPoints.getLat())
                    .and(building.longitude
                        .between(minPoints.getLng(), maxPoints.getLng())))
                .fetch();
    }
}
