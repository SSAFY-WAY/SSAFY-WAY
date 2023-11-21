package com.ssafy.ssafyway.house.mapper;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.domain.House;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {
    public House toEntity(RentRow row, Building building) {
        return House.builder()
                .building(building)
                .price(Integer.parseInt(row.getRentGtn()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .build();
    }
}
