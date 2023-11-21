package com.ssafy.ssafyway.region.data.cond;

import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RegionFilterCond {
    private String districtName;
    private String legalDongName;
    private int districtCode;
    private int legalDongCode;

    @Builder
    private RegionFilterCond(String districtName, String legalDongName, int districtCode, int legalDongCode) {
        this.districtName = districtName;
        this.legalDongName = legalDongName;
        this.districtCode = districtCode;
        this.legalDongCode = legalDongCode;
    }

    public static RegionFilterCond from(BuildingSearchRequest request) {
        return RegionFilterCond.builder()
                .districtName(request.getDistrictName())
                .districtCode(request.getDistrictCode())
                .legalDongName(request.getLegalDongName())
                .legalDongCode(request.getLegalDongCode())
                .build();
    }
}
