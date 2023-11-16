package com.ssafy.ssafyway.api.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class APIException extends GlobalException {
    public APIException(ErrorCode code) {
        super(code);
    }
}
