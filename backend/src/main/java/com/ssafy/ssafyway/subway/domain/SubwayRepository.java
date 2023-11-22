package com.ssafy.ssafyway.subway.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {
    boolean existsByName(String name);
    Optional<Subway> findSubwayByName(String name);
}
