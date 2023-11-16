package com.ssafy.ssafyway.region.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class RegionException extends GlobalException {
    public RegionException(ErrorCode code) {
        super(code);
    }
}
