package com.ssafy.ssafyway.building.data.vo;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.data.vo.HouseVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class BuildingSearchByRegionVO extends BuildingSearchVO{

    public static BuildingSearchByRegionVO from(Building building) {
        List<HouseVO> detailList = getHouseList(building);

        return BuildingSearchByRegionVO.builder()
                .lat(building.getPoints().getLat())
                .lng(building.getPoints().getLng())
                .buildingName(building.getBuildingName())
                .builtYear(building.getConstructionYear())
                .houseList(detailList)
                .houseCount(detailList.size())
                .build();
    }
}
