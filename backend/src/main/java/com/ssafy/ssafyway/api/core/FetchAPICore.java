package com.ssafy.ssafyway.api.core;

import com.ssafy.ssafyway.api.core.dto.APIRequest;
import com.ssafy.ssafyway.api.core.dto.APIResponse;

public interface FetchAPICore {
    APIResponse fetchAPI(APIRequest request);
}
