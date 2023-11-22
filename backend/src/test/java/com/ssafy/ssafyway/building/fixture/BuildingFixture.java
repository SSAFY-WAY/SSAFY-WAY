package com.ssafy.ssafyway.building.fixture;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingType;
import com.ssafy.ssafyway.region.domain.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum BuildingFixture {
    CREATE_HOUSE_GEO(0D, 0D, 0, 0, 0, "", BuildingType.아파트),
    GRAND_TOWER(37.4869292125081, 126.92967594474, 1425, 7, 2016, "그랜드타워", BuildingType.아파트),
    JU_GONG4(37.6553473821348, 127.076921363501, 356, 2, 1991, "주공4", BuildingType.아파트),
    SAM_DO(37.5624883732543, 127.163924681649, 233, 10, 1986, "삼도", BuildingType.연립다세대);
    private double longitude;
    private double latitude;
    private int bobn;
    private int bubn;
    private int constructionYear;
    private String buildingName;
    private BuildingType buildingType;

    public Building toHouseGeo(Region region) {
        return Building.builder()
                .region(region)
                .longitude(longitude)
                .latitude(latitude)
                .bobn(bobn)
                .bubn(bubn)
                .constructionYear(constructionYear)
                .buildingName(buildingName)
                .buildingType(buildingType)
                .build();
    }

    public Building toHouseGeoByRow(RentRow row, Region region) {
        return Building.builder()
                .latitude(0)
                .longitude(0)
                .constructionYear(Integer.parseInt(row.getBuildYear()))
                .buildingName(row.getBldgNm())
                .bobn(Integer.parseInt(row.getBobn()))
                .bubn(Integer.parseInt(row.getBubn()))
                .region(region)
                .build();
    }
}
