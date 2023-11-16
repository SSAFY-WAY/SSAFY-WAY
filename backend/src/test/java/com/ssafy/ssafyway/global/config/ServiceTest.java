package com.ssafy.ssafyway.global.config;

import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public abstract class ServiceTest {
    @Autowired
    MemberRepository memberRepository;

    public Member saveMember(MemberFixture fixture) {
        return memberRepository.save(fixture.toMember());
    }
}
