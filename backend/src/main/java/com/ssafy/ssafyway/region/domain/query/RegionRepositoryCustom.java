package com.ssafy.ssafyway.region.domain.query;

import com.ssafy.ssafyway.region.data.cond.RegionFilterCond;
import com.ssafy.ssafyway.region.data.vo.DistrictVO;
import com.ssafy.ssafyway.region.domain.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepositoryCustom {
    Optional<Region> findByFilterCond(RegionFilterCond cond);

    List<DistrictVO> findAllToDistrict();

    List<Region> findAllToLegalDong(int districtCode);
}
