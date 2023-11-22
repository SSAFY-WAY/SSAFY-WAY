package com.ssafy.ssafyway.api.seoulopendata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByHouseCond;
import com.ssafy.ssafyway.api.seoulopendata.data.dto.request.RentAPIRequest;
import com.ssafy.ssafyway.api.seoulopendata.data.dto.response.RentAPIResponse;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentFile;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.house.domain.House;
import com.ssafy.ssafyway.house.service.HouseService;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.service.BuildingService;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SeoulOpenDataService {

    private final RegionService regionService;
    private final BuildingService buildingService;
    private final HouseService houseService;
    private final SeoulOpenDataRentHouseFetchAPI seoulOpenDataRentHouseAPI;

    /**
     * API를 호출하여 전세집 데이터를 추출 및 DB에 저장한다 .
     *
     * @param devide HouseData를 어느 정도로 줄인건지에 대한 param
     */
    @Transactional
    public void fetchHouseData(int devide) {
        log.info("fetchHouseData method start");
        RentAPIResponse healthCheckResponse =
                (RentAPIResponse) seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.healthCheckRequest());
        int totalCount = healthCheckResponse.getTbLnOpendataRentV().getListTotalCount() / devide;

        for (int i = 1; i < totalCount; i += 999) {
            RentAPIResponse rentAPIResponse =
                    (RentAPIResponse) seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.toRequest(i, 999));
            saveHouseData(seoulOpenDataRentHouseAPI.filteringRentHouseByAPI(rentAPIResponse));
            log.info("data search {}%", (float) i / totalCount * 100);
        }
        log.info("fetchHouseData method end");
    }

    /**
     * 파일을 읽어 전세집 데이터를 추출 및 DB에 저장한다 .
     */
    @Transactional
    public void loadHouseData() throws IOException {
        log.info("loadHouseData method start");

        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("서울특별시_전월세가_2.json");
        RentFile file = objectMapper.readValue(resource.getInputStream(), RentFile.class);

        saveHouseData(seoulOpenDataRentHouseAPI.filteringRentHouseByFile(file));
        log.info("loadHouseData method end");
    }

    /**
     * 전세집 데이터를 DB에 저장한다. 저장 테이블은 region, building, house 이다.
     *
     * @param rentRows 공공데이터에서 뽑은 전세집 데이터들
     */
    private void saveHouseData(List<RentRow> rentRows) {
        for (RentRow row : rentRows) {
            try {
                Region region = regionService.getRegion(row);
                Building building = buildingService.getBuilding(row, region);
                Optional<House> optionalHouseDetail =
                        houseService.findByDataCond(ExistByHouseCond.from(row));
                if (optionalHouseDetail.isPresent()) {
                    optionalHouseDetail.get().modifyUpdateDateByNow();
                    continue;
                }
                houseService.saveHouseDetail(row, building);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }
}
