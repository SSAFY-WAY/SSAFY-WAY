package com.ssafy.ssafyway.auth.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class AuthException extends GlobalException {
    public AuthException(ErrorCode code) {
        super(code);
    }
}
