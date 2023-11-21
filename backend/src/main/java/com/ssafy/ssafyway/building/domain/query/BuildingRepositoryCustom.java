package com.ssafy.ssafyway.building.domain.query;

import com.ssafy.ssafyway.building.data.cond.BuildingFilterCond;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.subway.Points;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<Building> findByFilterCond(BuildingFilterCond cond);

    List<Building> findByBoundary(Points minPoints, Points maxPoints);
}
