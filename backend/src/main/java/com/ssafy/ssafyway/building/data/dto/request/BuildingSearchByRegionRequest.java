package com.ssafy.ssafyway.building.data.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BuildingSearchByRegionRequest extends BuildingSearchRequest{
    private String districtName;
    private String legalDongName;
    private int districtCode;
    private int legalDongCode;
}
