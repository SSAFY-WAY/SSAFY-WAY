package com.ssafy.ssafyway.house.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class HouseException extends GlobalException {
    public HouseException(ErrorCode code) {
        super(code);
    }
}
