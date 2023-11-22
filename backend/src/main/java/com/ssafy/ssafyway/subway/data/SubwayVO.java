package com.ssafy.ssafyway.subway.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SubwayVO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Line")
    private String line;
    private String x;
    private String y;
}
