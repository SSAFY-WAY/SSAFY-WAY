package com.ssafy.ssafyway.housegeo.domain.query;

import com.ssafy.ssafyway.housegeo.data.cond.HouseGeoFilterCond;
import com.ssafy.ssafyway.housegeo.domain.HouseGeo;

import java.util.List;

public interface HouseGeoRepositoryCustom {
    List<HouseGeo> findByFilterCond(HouseGeoFilterCond cond);
}
