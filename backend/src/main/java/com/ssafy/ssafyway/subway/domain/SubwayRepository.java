package com.ssafy.ssafyway.subway.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {
    boolean existsByName(String name);
}
