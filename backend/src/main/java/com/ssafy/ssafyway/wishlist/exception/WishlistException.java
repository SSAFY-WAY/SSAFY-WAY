package com.ssafy.ssafyway.wishlist.exception;

import com.ssafy.ssafyway.global.exception.ErrorCode;
import com.ssafy.ssafyway.global.exception.GlobalException;

public class WishlistException extends GlobalException {
    public WishlistException(ErrorCode code) {
        super(code);
    }
}
