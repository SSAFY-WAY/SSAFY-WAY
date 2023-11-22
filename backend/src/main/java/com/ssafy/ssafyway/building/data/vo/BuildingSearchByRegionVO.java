package com.ssafy.ssafyway.building.data.vo;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.data.vo.HouseVO;
import com.ssafy.ssafyway.region.domain.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class BuildingSearchByRegionVO extends BuildingSearchVO{

    public static BuildingSearchByRegionVO of(Region region, Building building) {
        List<HouseVO> detailList = getHouseList(building);

        return BuildingSearchByRegionVO.builder()
                .regionName(String.format("%s %s", region.getDistrictName(), region.getLegalDongName()))
                .lat(building.getPoints().getLat())
                .lng(building.getPoints().getLng())
                .buildingName(building.getBuildingName())
                .builtYear(building.getConstructionYear())
                .houseList(detailList)
                .houseCount(detailList.size())
                .build();
    }
}
