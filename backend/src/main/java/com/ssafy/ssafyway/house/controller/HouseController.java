package com.ssafy.ssafyway.house.controller;

import com.ssafy.ssafyway.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/house")
@RequiredArgsConstructor
@RestController
public class HouseController {

    private final HouseService houseService;
}
