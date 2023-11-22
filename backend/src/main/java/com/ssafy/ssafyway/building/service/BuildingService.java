package com.ssafy.ssafyway.building.service;

import com.ssafy.ssafyway.api.kakao.data.vo.Documents;
import com.ssafy.ssafyway.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.building.data.cond.BuildingFilterCond;
import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchByRegionRequest;
import com.ssafy.ssafyway.building.data.dto.request.BuildingSearchBySubwayRequest;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchByRegionResponse;
import com.ssafy.ssafyway.building.data.dto.response.BuildingSearchBySubwayResponse;
import com.ssafy.ssafyway.building.data.vo.BuildingSearchByRegionVO;
import com.ssafy.ssafyway.building.data.vo.BuildingSearchBySubwayVO;
import com.ssafy.ssafyway.building.data.vo.SubwayProximity;
import com.ssafy.ssafyway.building.domain.Building;
import com.ssafy.ssafyway.building.domain.BuildingRepository;
import com.ssafy.ssafyway.building.exception.BuildingErrorCode;
import com.ssafy.ssafyway.building.exception.BuildingException;
import com.ssafy.ssafyway.building.mapper.BuildingMapper;
import com.ssafy.ssafyway.global.domain.Points;
import com.ssafy.ssafyway.region.data.cond.RegionFilterCond;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.service.RegionService;
import com.ssafy.ssafyway.subway.domain.Subway;
import com.ssafy.ssafyway.subway.mapper.SubwayMapper;
import com.ssafy.ssafyway.subway.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final KakaoMapFetchAPI kakaoMapFetchAPI;
    private final BuildingMapper buildingMapper;
    private final RegionService regionService;
    private final SubwayService subwayService;

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

    public BuildingSearchByRegionResponse findByRegion(BuildingSearchByRegionRequest request) {
        Region findRegion = regionService.findByFilter(RegionFilterCond.from(request));
        List<BuildingSearchByRegionVO> buildingList = buildingRepository.findByFilterCond(BuildingFilterCond.of(findRegion.getId(), request))
            .stream()
            .map(building -> BuildingSearchByRegionVO.of(findRegion, building))
            .collect(Collectors.toList());
        return BuildingSearchByRegionResponse.of(buildingList);
    }

    public BuildingSearchBySubwayResponse findBySubway(BuildingSearchBySubwayRequest request) {
        List<Subway> subwayList = subwayService.getSubwayList(request.getSubwayNameList());
        double limDist = request.getDistance();

        List<BuildingSearchBySubwayVO> resultList = new ArrayList<>();
        for (Map.Entry<Integer, SubwayProximity> entry : mapByProximity(subwayList, limDist).entrySet()) {
            Integer buildingId = entry.getKey();
            SubwayProximity subwayProximity = entry.getValue();
            resultList.add(BuildingSearchBySubwayVO.of(subwayProximity, findById(buildingId)));
        }
        return BuildingSearchBySubwayResponse.of(SubwayMapper.toSubwayVOs(subwayList), resultList, limDist);
    }

    private Map<Integer, SubwayProximity> mapByProximity(List<Subway> subwayList, double limitDist) {
        Map<Integer, SubwayProximity> proxMap = new HashMap<>();

        for (Subway subway : subwayList) {
            List<Building> buildingList = findBuildingsInBoundary(subway, limitDist);
            for (Building building : buildingList) {
                double dist = getDistance(subway.getPoints(), building.getPoints());
                if (dist <= limitDist) {
                    SubwayProximity existingProx = proxMap.get(building.getId());
                    if (existingProx == null || dist < existingProx.getProximity()) {
                        proxMap.put(building.getId(), new SubwayProximity(subway.getName(), dist));
                    }
                }
            }
        }
        return proxMap;
    }

    private List<Building> findBuildingsInBoundary(Subway subway, double distance) {
        Points centerPoint = subway.getPoints();
        Points minPoints = centerPoint.getMinPoints(distance);
        Points maxPoints = centerPoint.getMaxPoints(distance);

        return buildingRepository.findByBoundary(minPoints, maxPoints);
    }

    private double getDistance(Points p1, Points p2) {
        double dLat = Math.toRadians(p2.getLat() - p1.getLat());
        double dLon = Math.toRadians(p2.getLng() - p1.getLng());

        double tmp1 = Math.sin(dLat/2)
                * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(p1.getLat()))
                * Math.cos(Math.toRadians(p2.getLat()))
                * Math.sin(dLon/2)
                * Math.sin(dLon/2);
        double tmp2 = 2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1-tmp1));
        return Points.EARTH_RADIUS * tmp2;    // Distance in m
    }

    private Building findById(int buildingId) {
        return buildingRepository.findById(buildingId)
                .orElseThrow(() -> new BuildingException(BuildingErrorCode.ERROR_CLIENT_BY_BUILDING_IS_NOT_EXIST));
    }
}
