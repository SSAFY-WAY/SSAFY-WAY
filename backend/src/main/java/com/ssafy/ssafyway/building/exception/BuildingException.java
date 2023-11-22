package com.ssafy.ssafyway.building.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class BuildingException extends GlobalException {
    public BuildingException(ErrorCode code) {
        super(code);
    }
}
