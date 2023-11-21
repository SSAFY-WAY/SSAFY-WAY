package com.ssafy.ssafyway.building.data.vo;

import com.ssafy.ssafyway.house.data.vo.HouseVO;
import com.ssafy.ssafyway.building.domain.Building;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class BuildingSearchByRegionVO {
    private double lat;
    private double lng;
    private String buildingName;
    private int builtYear;
    private List<HouseVO> houseDetailList;
    private int houseDetailCount;

    public static BuildingSearchByRegionVO from(Building geo) {
        List<HouseVO> detailList = getHouseDetailList(geo);
        return BuildingSearchByRegionVO.builder()
                .lat(geo.getLatitude())
                .lng(geo.getLongitude())
                .buildingName(geo.getBuildingName())
                .builtYear(geo.getConstructionYear())
                .houseDetailList(detailList)
                .houseDetailCount(detailList.size())
                .build();
    }

    public static List<HouseVO> getHouseDetailList(Building geo) {
        return geo.getHouseList().stream()
                .map(HouseVO::from)
                .collect(Collectors.toList());
    }
}
