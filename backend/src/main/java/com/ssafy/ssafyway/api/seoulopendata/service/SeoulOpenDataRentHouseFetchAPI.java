package com.ssafy.ssafyway.api.seoulopendata.service;

import com.ssafy.ssafyway.api.core.FetchAPICore;
import com.ssafy.ssafyway.api.core.dto.APIRequest;
import com.ssafy.ssafyway.api.core.dto.APIResponse;
import com.ssafy.ssafyway.api.seoulopendata.data.dto.request.RentAPIRequest;
import com.ssafy.ssafyway.api.seoulopendata.data.dto.response.RentAPIResponse;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentFile;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SeoulOpenDataRentHouseFetchAPI implements FetchAPICore {
    @Value("${seouldata.tbLnOpendataRentV.key}")
    private String key;

    /**
     * 공공데이터에서 전세집 데이터를 추출한다.
     */
    @Override
    public APIResponse fetchAPI(APIRequest request) {
        RentAPIRequest rentAPIRequest = (RentAPIRequest) request;
        String urlBuilder = "http://openapi.seoul.go.kr:8088"
                + '/' + URLEncoder.encode(key, StandardCharsets.UTF_8)
                + '/' + URLEncoder.encode("json", StandardCharsets.UTF_8)
                + '/' + URLEncoder.encode("tbLnOpendataRentV", StandardCharsets.UTF_8)
                + '/' + URLEncoder.encode(String.valueOf(rentAPIRequest.getStart()), StandardCharsets.UTF_8)
                + '/' + URLEncoder.encode(String.valueOf(rentAPIRequest.getEnd()), StandardCharsets.UTF_8);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlBuilder, RentAPIResponse.class);
    }

    /**
     * 전세집 중 필요한 데이터만 추출하기 위한 필터링기능
     *
     * @param rentAPIResponse 공공데이터 API의 전세집 데이터
     * @return @link package.class#member label
     */
    public List<RentRow> filteringRentHouseByAPI(RentAPIResponse rentAPIResponse) {
        return filtering(rentAPIResponse.getTbLnOpendataRentV().getRow());
    }

    /**
     * 전세집 중 필요한 데이터만 추출하기 위한 필터링기능
     *
     * @param rentFile 공공데이터 File의 전세집 데이터
     * @return @link package.class#member label
     */
    public List<RentRow> filteringRentHouseByFile(RentFile rentFile) {
        return filtering(rentFile.getRow());
    }

    private List<RentRow> filtering(List<RentRow> rentRows) {
        return rentRows.stream()
                .filter(row -> row.getRentGbn().equals("전세"))
                .filter(row -> row.getCntrctPrd() == null || row.getCntrctPrd().isBlank())
                .filter(row -> isValid(row.getBobn()))
                .filter(row -> isValid(row.getBubn()))
                .filter(row -> isValid(row.getBjdongCd()))
                .filter(row -> isValid(row.getBjdongNm()))
                .filter(row -> isValid(row.getSggCd()))
                .filter(row -> isValid(row.getSggNm()))
                .filter(row -> isValid(row.getRentGtn()) && !row.getRentGtn().equals("0"))
                .filter(row -> isValid(row.getBuildYear()))
                .filter(row -> row.getRentArea() != null && row.getRentArea() != 0D)
                .filter(row -> row.getFlrNo() != null && row.getFlrNo() != 0D)
                .collect(Collectors.toList());
    }

    private boolean isValid(String field) {
        return field != null && !field.isBlank();
    }

}
