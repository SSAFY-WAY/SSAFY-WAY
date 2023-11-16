package com.ssafy.ssafyway.housedetail.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class HouseDetailException extends GlobalException {
    public HouseDetailException(ErrorCode code) {
        super(code);
    }
}
