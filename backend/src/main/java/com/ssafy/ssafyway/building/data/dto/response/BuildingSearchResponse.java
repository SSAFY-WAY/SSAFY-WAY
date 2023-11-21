package com.ssafy.ssafyway.building.data.dto.response;

import com.ssafy.ssafyway.building.data.vo.BuildingSearchByRegionVO;
import com.ssafy.ssafyway.region.domain.Region;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BuildingSearchResponse {
    private String regionName;  // 자치구 + 법정동
    private List<BuildingSearchByRegionVO> houseGeoList;
    private int houseGeoCount;

    public static BuildingSearchResponse of(Region region, List<BuildingSearchByRegionVO> list) {
        return BuildingSearchResponse.builder()
                .regionName(String.format("%s %s", region.getDistrictName(), region.getLegalDongName()))
                .houseGeoList(list)
                .houseGeoCount(list.size())
                .build();
    }
}
