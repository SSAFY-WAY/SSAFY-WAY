package com.ssafy.ssafyway.region.controller;

import com.ssafy.ssafyway.region.data.dto.response.DistrictResponse;
import com.ssafy.ssafyway.region.data.dto.response.LegalDongResponse;
import com.ssafy.ssafyway.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/region")
@RestController
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/find/district")
    public ResponseEntity<DistrictResponse> searchDistrict() {
        DistrictResponse response = regionService.findDistrictAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/legal")
    public ResponseEntity<LegalDongResponse> searchLegalDong(@RequestParam int districtCode) {
        LegalDongResponse response = regionService.findLegalDongAll(districtCode);
        return ResponseEntity.ok(response);
    }
}
