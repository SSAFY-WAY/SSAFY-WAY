package com.ssafy.ssafyway.building.data.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({ "buildingName", "regionName", "lat", "lng", "builtYear", "nearestSubwayName", "distanceFromSubway", "houseCount", "houseList" })
public class BuildingSearchBySubwayVO extends BuildingSearchVO{

    private String nearestSubwayName;
    private int distanceFromSubway; // 단위 : m
    private String regionName;

    public static BuildingSearchBySubwayVO of(SubwayProximity subwayProximity, Building building) {
        List<HouseVO> detailList = getHouseList(building);
        Region region = building.getRegion();

        return BuildingSearchBySubwayVO.builder()
                .regionName(String.format("%s %s", region.getDistrictName(), region.getLegalDongName()))
                .lat(building.getPoints().getLat())
                .lng(building.getPoints().getLng())
                .nearestSubwayName(subwayProximity.getSubwayName() + "역")
                .distanceFromSubway((int)Math.round(subwayProximity.getProximity()))
                .buildingName(building.getBuildingName())
                .builtYear(building.getConstructionYear())
                .houseList(detailList)
                .houseCount(detailList.size())
                .build();
    }
}
