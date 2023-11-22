package com.ssafy.ssafyway.subway.data;

import com.ssafy.ssafyway.global.domain.Points;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embedded;

@ToString
@Builder
@Getter
public class SubwayVO {
    private String name;
    @Embedded
    private Points points;
}
