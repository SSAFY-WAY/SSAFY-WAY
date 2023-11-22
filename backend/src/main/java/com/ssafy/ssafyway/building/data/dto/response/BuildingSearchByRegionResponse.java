package com.ssafy.ssafyway.building.data.dto.response;

import com.ssafy.ssafyway.building.data.vo.BuildingSearchByRegionVO;
import com.ssafy.ssafyway.region.domain.Region;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BuildingSearchByRegionResponse {
    private String regionName;  // 자치구 + 법정동
    private List<BuildingSearchByRegionVO> buildingList;
    private int buildingCount;

    public static BuildingSearchByRegionResponse of(Region region, List<BuildingSearchByRegionVO> buildingList) {
        return BuildingSearchByRegionResponse.builder()
                .regionName(String.format("%s %s", region.getDistrictName(), region.getLegalDongName()))
                .buildingList(buildingList)
                .buildingCount(buildingList.size())
                .build();
    }
}
