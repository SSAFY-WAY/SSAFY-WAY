package com.ssafy.ssafyway.housedetail.controller;

import com.ssafy.ssafyway.auth.annotation.Authenticated;
import com.ssafy.ssafyway.auth.vo.AuthMember;
import com.ssafy.ssafyway.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.ssafyway.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.ssafyway.housedetail.service.HouseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/house")
@RequiredArgsConstructor
@RestController
public class HouseDetailController {

    private final HouseDetailService houseDetailService;

    @PostMapping("/auth/recent")
    public ResponseEntity<HouseDetailRecentViewResponse> recentView(
            @Authenticated AuthMember authMember,
            @RequestBody HouseDetailRecentViewRequest request) {
        HouseDetailRecentViewResponse response = houseDetailService.findByRecentView(authMember, request);
        return ResponseEntity.ok(response);
    }
}
