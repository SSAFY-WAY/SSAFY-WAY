package com.ssafy.ssafyway.building.data.cond;

import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchByRegionRequest;
import com.ssafy.ssafyway.building.domain.BuildingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BuildingFilterCond {
    private static final Double AREA_UNITS = 3.3D;
    private static final int MONEY_UNITS = 1000;

    private int regionId;
    private int minPrice;
    private int maxPrice;
    private double minArea;
    private double maxArea;
    private List<BuildingType> types;

    @Builder
    public BuildingFilterCond(
            int regionId, int minPrice, int maxPrice, double minArea, double maxArea,
            List<BuildingType> types) {
        this.regionId = regionId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.types = types;
    }

    public static BuildingFilterCond of(int regionId, BuildingSearchByRegionRequest request) {
        return BuildingFilterCond.builder()
                .regionId(regionId)
                .minArea(request.getMinArea() * AREA_UNITS)
                .maxArea(request.getMaxArea() * AREA_UNITS)
                .minPrice(request.getMinPrice() * MONEY_UNITS)
                .maxPrice(request.getMaxPrice() * MONEY_UNITS)
                .types(request.getTypes())
                .build();
    }
}
