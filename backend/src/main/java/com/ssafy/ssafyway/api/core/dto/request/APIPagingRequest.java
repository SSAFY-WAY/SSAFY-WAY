package com.ssafy.ssafyway.api.core.dto.request;

import com.ssafy.ssafyway.api.core.dto.APIRequest;

public interface APIPagingRequest extends APIRequest {
    int getStart();

    int getEnd();

    int getSize();
}
