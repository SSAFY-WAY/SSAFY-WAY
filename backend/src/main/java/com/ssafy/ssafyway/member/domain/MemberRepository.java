package com.ssafy.ssafyway.member.domain;

import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.domain.query.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
    Optional<Member> findByEmail(Email email);
}
