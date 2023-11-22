package com.ssafy.ssafyway.house.fixture;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.house.domain.House;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum HouseFixture {
    CREATE_HOUSE_DETAIL(0, 0D, 0),
    GRAND_TOWER_3(25200, 29.98, 3),
    JU_GONG4_1(35000, 59.39, 1),
    JU_GONG4_6(21000, 45.77, 6),
    SAN_DO_3(9372, 41.14, 3);
    private int price;
    private double area;
    private int floor;

    public House toHouseDetail(Building geo) {
        return House.builder()
                .building(geo)
                .price(price)
                .area(area)
                .floor(floor)
                .build();
    }

    public House toHouseDetail(Building geo, RentRow row) {
        return House.builder()
                .building(geo)
                .price(Integer.parseInt(row.getRentFee()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .build();
    }
}
