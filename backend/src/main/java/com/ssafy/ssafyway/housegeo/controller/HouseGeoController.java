package com.ssafy.ssafyway.housegeo.controller;

import com.ssafy.ssafyway.auth.annotation.Authenticated;
import com.ssafy.ssafyway.auth.vo.AuthMember;
import com.ssafy.ssafyway.housegeo.data.dto.request.GeoSearchRequest;
import com.ssafy.ssafyway.housegeo.data.dto.response.HouseGeoSearchResponse;
import com.ssafy.ssafyway.housegeo.service.HouseGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/geo")
@RestController
public class HouseGeoController {
    private final HouseGeoService houseGeoService;

    @PostMapping("/auth/search")
    public ResponseEntity<HouseGeoSearchResponse> search(
            @Authenticated AuthMember authMember,
            @RequestBody GeoSearchRequest request) {
        HouseGeoSearchResponse response = houseGeoService.findByFilter(request);
        return ResponseEntity.ok(response);
    }
}
