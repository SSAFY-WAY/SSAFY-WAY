package com.ssafy.ssafyway.region.service;

import com.ssafy.ssafyway.api.seoulopendata.data.vo.RentRow;
import com.ssafy.ssafyway.region.data.cond.RegionFilterCond;
import com.ssafy.ssafyway.region.data.dto.response.DistrictResponse;
import com.ssafy.ssafyway.region.data.dto.response.LegalDongResponse;
import com.ssafy.ssafyway.region.data.vo.DistrictVO;
import com.ssafy.ssafyway.region.data.vo.LegalDongVO;
import com.ssafy.ssafyway.region.domain.Region;
import com.ssafy.ssafyway.region.domain.RegionRepository;
import com.ssafy.ssafyway.region.exception.RegionErrorCode;
import com.ssafy.ssafyway.region.exception.RegionException;
import com.ssafy.ssafyway.region.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    /**
     * Region 데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     *
     * @param row 공공데이터의 전세집 데이터
     * @return Region 참조
     */
    public Region getRegion(RentRow row) {
        Optional<Region> region =
                regionRepository.findByDistrictCodeAndLegalDongCode(
                        Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd()));
        return region.orElseGet(() -> regionRepository.save(regionMapper.toEntity(row)));
    }

    public Region findByFilter(RegionFilterCond cond) {
        return regionRepository.findByFilterCond(cond)
                .orElseThrow(() -> new RegionException(RegionErrorCode.ERROR_CLIENT_BY_IS_NOT_EXISTED_REGION));
    }

    public DistrictResponse findDistrictAll() {
        List<DistrictVO> list = regionRepository.findAllToDistrict();
        return DistrictResponse.from(list);
    }

    public LegalDongResponse findLegalDongAll(int districtCode) {
        List<Region> regions = regionRepository.findAllToLegalDong(districtCode);
        return LegalDongResponse.from(regions.stream()
                .map(LegalDongVO::from)
                .collect(Collectors.toList()));
    }
}
