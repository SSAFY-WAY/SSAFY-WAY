package com.ssafy.ssafyway.subway.domain;

import com.ssafy.ssafyway.subway.Points;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subway {

    private static final double EARTH_RADIUS = 6371e3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Embedded
    private Points points;

    private Subway(String name, Points points) {
        this.name = name;
        this.points = points;
    }

    public static Subway of(String name, double lat, double lon) {
        return new Subway(name, new Points(lat, lon));
    }
}
