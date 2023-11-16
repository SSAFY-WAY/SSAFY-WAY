package com.ssafy.ssafyway.api.seoulopendata.data.cond;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class ExistByDetailCond {
    private int districtCode;
    private int legalDongCode;
    private int bobn;
    private int bubn;
    private double area;
    private int floor;
    private int price;

    public static ExistByDetailCond from(RentRow row) {
        return ExistByDetailCond.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .bobn(Integer.parseInt(row.getBobn()))
                .bubn(Integer.parseInt(row.getBubn()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .price(Integer.parseInt(row.getRentGtn()))
                .build();
    }
}
