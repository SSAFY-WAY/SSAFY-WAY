package com.ssafy.ssafyway.member.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class MemberException extends GlobalException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
