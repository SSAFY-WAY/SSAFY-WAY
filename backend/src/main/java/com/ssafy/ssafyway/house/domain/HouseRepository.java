package com.ssafy.ssafyway.house.domain;

import com.ssafy.ssafyway.house.domain.query.HouseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer>, HouseRepositoryCustom {
}
