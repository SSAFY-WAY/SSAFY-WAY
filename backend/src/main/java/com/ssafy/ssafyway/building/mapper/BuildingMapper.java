package com.ssafy.ssafyway.building.mapper;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingType;
import com.ssafy.ssafyway.region.domain.Region;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
    public Building toEntity(RentRow row, Documents position, Region region) {
        return Building.builder()
                .latitude(Double.parseDouble(position.getY()))
                .longitude(Double.parseDouble(position.getX()))
                .bobn(Integer.parseInt(row.getBobn()))
                .bubn(Integer.parseInt(row.getBubn()))
                .buildingName(row.getBldgNm())
                .constructionYear(Integer.parseInt(row.getBuildYear()))
                .buildingType(BuildingType.valueOf(row.getHouseGbnNm()))
                .region(region)
                .build();
    }
}
