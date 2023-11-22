package com.ssafy.ssafyway.subway.mapper;

import com.ssafy.ssafyway.subway.data.SubwayVO;
import com.ssafy.ssafyway.subway.domain.Subway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubwayMapper {
    public static List<SubwayVO> toSubwayVOs(List<Subway> subways) {
        return subways.stream()
                .map(subway -> SubwayVO.builder()
                        .name(subway.getName())
                        .points(subway.getPoints())
                        .build())
                .collect(Collectors.toList());

    }
}
