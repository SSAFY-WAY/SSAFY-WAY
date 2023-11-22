package com.ssafy.ssafyway.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer> {
}
