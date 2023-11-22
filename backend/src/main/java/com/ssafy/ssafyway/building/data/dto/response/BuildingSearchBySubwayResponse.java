package com.ssafy.ssafyway.building.data.dto.response;

import com.ssafy.ssafyway.building.data.vo.BuildingSearchBySubwayVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BuildingSearchBySubwayResponse {
    private List<BuildingSearchBySubwayVO> buildingList;
    private int buildingCount;

    public static BuildingSearchBySubwayResponse from(List<BuildingSearchBySubwayVO> buildingList) {
        return BuildingSearchBySubwayResponse.builder()
                .buildingList(buildingList)
                .buildingCount(buildingList.size())
                .build();
    }
}
