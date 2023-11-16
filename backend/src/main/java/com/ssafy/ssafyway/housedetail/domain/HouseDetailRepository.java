package com.ssafy.ssafyway.housedetail.domain;

import com.ssafy.ssafyway.housedetail.domain.query.HouseDetailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseDetailRepository extends JpaRepository<HouseDetail, Integer>, HouseDetailRepositoryCustom {
}
