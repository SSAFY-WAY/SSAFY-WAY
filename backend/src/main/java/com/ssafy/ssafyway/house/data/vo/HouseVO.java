package com.ssafy.ssafyway.house.data.vo;

import com.ssafy.ssafyway.house.domain.House;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class HouseVO {
    private int houseDetailId;
    private int price;
    private int area;
    private int floor;

    public static HouseVO from(House detail) {
        return HouseVO.builder()
                .houseDetailId(detail.getId())
                .price(detail.getPrice())
                .area((int) Math.round(detail.getArea() / 3.3))
                .floor(detail.getFloor())
                .build();
    }
}
