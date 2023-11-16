package com.ssafy.ssafyway.api.kakao.data.dto.request;

import com.ssafy.ssafyway.api.core.dto.request.APIQueryRequest;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class KakaoAPIRequest implements APIQueryRequest {
    private String query;

    public static KakaoAPIRequest toRequest(String query) {
        return new KakaoAPIRequest(query);
    }
}
