package com.ssafy.ssafyway.building.controller;

import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchRequest;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchResponse;
import com.ssafy.ssafyway.building.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/geo")
@RestController
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping("/auth/search")
    public ResponseEntity<BuildingSearchResponse> search(
            @RequestBody BuildingSearchRequest request) {
        BuildingSearchResponse response = buildingService.findByFilter(request);
        return ResponseEntity.ok(response);
    }
}
