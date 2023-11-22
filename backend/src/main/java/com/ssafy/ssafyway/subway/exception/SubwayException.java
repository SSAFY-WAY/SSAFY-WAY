package com.ssafy.ssafyway.subway.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class SubwayException extends GlobalException {
    public SubwayException(ErrorCode code) {
        super(code);
    }
}
