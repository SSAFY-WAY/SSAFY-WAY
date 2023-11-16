package com.ssafy.ssafyway.api.core.dto.request;

import com.ssafy.ssafyway.api.core.dto.APIRequest;

public interface APIQueryRequest extends APIRequest {
    String getQuery();
}
