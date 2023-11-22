package com.ssafy.ssafyway.subway.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum SubwayErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_SUBWAY_IS_NOT_EXIST(400, "SUBWAY_01", "존재하지 않는 지하철 역 이름 입니다.");
    private final int statusCode;
    private final String errorCode;
    private final String message;

    SubwayErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
