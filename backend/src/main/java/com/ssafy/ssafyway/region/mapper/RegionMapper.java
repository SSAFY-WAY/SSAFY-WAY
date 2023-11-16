package com.ssafy.ssafyway.region.mapper;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.region.domain.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    public Region toEntity(RentRow row) {
        return Region.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .build();
    }
}
