package com.ssafy.ssafyway.building.data.dto.response;

import com.ssafy.ssafyway.building.data.vo.BuildingSearchByRegionVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BuildingSearchByRegionResponse {
    private List<BuildingSearchByRegionVO> buildingList;
    private int buildingCount;

    public static BuildingSearchByRegionResponse of(List<BuildingSearchByRegionVO> buildingList) {
        return BuildingSearchByRegionResponse.builder()
                .buildingList(buildingList)
                .buildingCount(buildingList.size())
                .build();
    }
}
