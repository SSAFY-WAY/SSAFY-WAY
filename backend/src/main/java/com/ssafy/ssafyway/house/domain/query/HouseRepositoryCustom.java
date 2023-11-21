package com.ssafy.ssafyway.house.domain.query;

import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByHouseCond;
import com.ssafy.ssafyway.house.domain.House;

import java.util.Optional;

public interface HouseRepositoryCustom {
    Optional<House> findByDataCond(ExistByHouseCond cond);
}
