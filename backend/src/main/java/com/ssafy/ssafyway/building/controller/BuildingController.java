package com.ssafy.ssafyway.building.controller;

import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchByRegionRequest;
import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchBySubwayRequest;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchByRegionResponse;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchBySubwayResponse;
import com.ssafy.ssafyway.building.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/building")
@RestController
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping("/search-region")
    public ResponseEntity<BuildingSearchByRegionResponse> search(
            @RequestBody BuildingSearchByRegionRequest request) {
        BuildingSearchByRegionResponse response = buildingService.findByRegion(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search-subway")
    public ResponseEntity<BuildingSearchBySubwayResponse> search(
            @RequestBody BuildingSearchBySubwayRequest request) {
        BuildingSearchBySubwayResponse response = buildingService.findBySubway(request);
        return ResponseEntity.ok(response);
    }
}
