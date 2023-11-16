package com.ssafy.ssafyway.api.kakao.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KakaoProperties {
    private final String baseUrl = "http://dapi.kakao.com";

    @Value("${kakao.api.url}")
    private String restApiKey;

    public String getDefaultHeader() {
        return "KakaoAK " + restApiKey;
    }
}