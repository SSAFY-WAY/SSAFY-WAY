package com.ssafy.ssafyway.building.data.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BuildingSearchBySubwayRequest extends BuildingSearchRequest{
    private List<String> subwayNameList;
    private int distance;
}
