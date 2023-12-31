package com.ssafy.ssafyway.building.data.vo;

import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.data.vo.HouseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class BuildingSearchVO {
    protected String buildingName;
    protected String regionName;
    protected double lat;
    protected double lng;
    protected int builtYear;
    protected List<HouseVO> houseList;
    protected int houseCount;

    protected static List<HouseVO> getHouseList(Building building) {
        return building.getHouseList().stream()
                .map(HouseVO::from)
                .collect(Collectors.toList());
    }

}
