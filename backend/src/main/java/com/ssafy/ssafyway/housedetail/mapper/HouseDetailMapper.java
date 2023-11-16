package com.ssafy.ssafyway.housedetail.mapper;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;
import org.springframework.stereotype.Component;

@Component
public class HouseDetailMapper {
    public HouseDetail toEntity(RentRow row, HouseGeo houseGeo) {
        return HouseDetail.builder()
                .houseGeo(houseGeo)
                .price(Integer.parseInt(row.getRentGtn()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .build();
    }
}
