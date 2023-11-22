package com.ssafy.ssafyway.building.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum BuildingErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_BUILDING_IS_NOT_EXIST(400, "BUILDING_01", "존재하지 않는 건물 입니다.");
    private final int statusCode;
    private final String errorCode;
    private final String message;

    BuildingErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
