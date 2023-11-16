package com.ssafy.ssafyway.housegeo.mapper;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.housegeo.domain.BuildingType;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;
import com.ssafy.ssafyway.region.domain.Region;
import org.springframework.stereotype.Component;

@Component
public class HouseGeoMapper {
    public HouseGeo toEntity(RentRow row, Documents position, Region region) {
        return HouseGeo.builder()
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
