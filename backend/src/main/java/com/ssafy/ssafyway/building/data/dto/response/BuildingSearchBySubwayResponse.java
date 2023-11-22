package com.ssafy.ssafyway.building.data.dto.response;

import com.ssafy.ssafyway.building.data.vo.BuildingSearchBySubwayVO;
import com.ssafy.ssafyway.subway.data.SubwayVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BuildingSearchBySubwayResponse {
    private List<BuildingSearchBySubwayVO> buildingList;
    private int buildingCount;
    private List<SubwayVO> subwayList;
    private int subwayCount;
    private int setDistance;

    public static BuildingSearchBySubwayResponse of(
            List<SubwayVO> subwayList,
            List<BuildingSearchBySubwayVO> buildingList,
            double setDistance) {
        return BuildingSearchBySubwayResponse.builder()
                .buildingList(buildingList)
                .buildingCount(buildingList.size())
                .subwayList(subwayList)
                .subwayCount(subwayList.size())
                .setDistance((int)setDistance)
                .build();
    }
}
