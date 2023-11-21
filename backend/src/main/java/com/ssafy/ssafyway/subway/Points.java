package com.ssafy.ssafyway.subway;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Points {

    public static final double EARTH_RADIUS = 6371e3; // in meters
    
    @Column(name = "latitude")
    private double lat;   // 위도
    @Column(name = "longitude")
    private double lon;   // 경도

}
