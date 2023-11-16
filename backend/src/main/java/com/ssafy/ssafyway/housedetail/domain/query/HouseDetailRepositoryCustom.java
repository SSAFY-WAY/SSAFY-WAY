package com.ssafy.ssafyway.housedetail.domain.query;

import com.ssafy.ssafyway.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.ssafyway.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.ssafyway.housedetail.domain.HouseDetail;

import java.util.List;
import java.util.Optional;

public interface HouseDetailRepositoryCustom {
    List<HouseDetail> findByRecentViewCond(HouseDetailRecentViewCond cond);

    Optional<HouseDetail> findByDataCond(ExistByDetailCond cond);
}
