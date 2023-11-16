package com.ssafy.ssafyway.housegeo.domain;

import com.ssafy.ssafyway.housegeo.domain.query.HouseGeoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseGeoRepository extends JpaRepository<HouseGeo, Integer>, HouseGeoRepositoryCustom {
    Optional<HouseGeo> findByBobnAndBubn(int bobn, int bubn);
}
