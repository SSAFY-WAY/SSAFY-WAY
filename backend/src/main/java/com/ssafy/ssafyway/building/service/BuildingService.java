package com.ssafy.ssafyway.building.service;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.data.cond.BuildingFilterCond;
import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchRequest;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchResponse;
import com.ssafy.ssafyway.building.data.vo.BuildingSearchByRegionVO;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.mapper.BuildingMapper;
import com.ssafy.ssafyway.region.data.cond.RegionFilterCond;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    private final RegionService regionService;

    private final KakaoMapFetchAPI kakaoMapFetchAPI;

    /**
     * Building 데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     *
     * @param row    공공데이터의 전세집 데이터
     * @param region region 참조
     * @return Building 참조
     */
    public Building getBuilding(RentRow row, Region region) {
        Optional<Building> building = buildingRepository.findByBobnAndBubn(
                Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn()));
        return building.orElseGet(() -> {
            Documents position = kakaoMapFetchAPI.kakaoAddressSearch(
                    row.getSggNm(), row.getBjdongNm(), row.getBobn(), row.getBubn());
            return buildingRepository.save(buildingMapper.toEntity(row, position, region));
        });
    }

    public BuildingSearchResponse findByFilter(BuildingSearchRequest request) {
        Region findRegion = regionService.findByFilter(RegionFilterCond.from(request));
        List<BuildingSearchByRegionVO> buildingList = buildingRepository
                .findByFilterCond(BuildingFilterCond.of(findRegion.getId(), request))
                .stream().map(BuildingSearchByRegionVO::from)
                .collect(Collectors.toList());
        return BuildingSearchResponse.of(findRegion, buildingList);
    }
}
