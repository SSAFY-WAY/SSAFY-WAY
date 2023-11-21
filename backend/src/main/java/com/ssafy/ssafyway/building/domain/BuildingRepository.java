package com.ssafy.ssafyway.building.domain;

import com.ssafy.ssafyway.building.domain.query.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Integer>, BuildingRepositoryCustom {
    Optional<Building> findByBobnAndBubn(int bobn, int bubn);
}
