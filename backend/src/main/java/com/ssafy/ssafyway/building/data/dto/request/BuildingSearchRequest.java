package com.ssafy.ssafyway.building.data.dto.request;

import com.ssafy.ssafyway.building.domain.BuildingType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public abstract class BuildingSearchRequest {
    private int minPrice;
    private int maxPrice;
    private int minArea;
    private int maxArea;
    private List<BuildingType> types;
}
