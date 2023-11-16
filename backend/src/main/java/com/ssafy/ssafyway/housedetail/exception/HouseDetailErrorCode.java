package com.ssafy.ssafyway.housedetail.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum HouseDetailErrorCode implements ErrorCode {
    ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED(400, "HOUSE_DETAIL_01", "존재하지 않는 매물 입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    HouseDetailErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
