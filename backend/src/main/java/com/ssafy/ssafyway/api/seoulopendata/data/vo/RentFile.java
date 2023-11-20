package com.ssafy.ssafyway.api.seoulopendata.data.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class RentFile {
    private final List<RentRow> row;

    @JsonCreator
    public RentFile(List<RentRow> row) {
        this.row = row;
    }
}
