package com.ssafy.ssafyway.member.service;

import com.ssafy.ssafyway.global.fixture.MemberFixture;
import com.ssafy.ssafyway.member.data.dto.request.MemberSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatCode;

@ActiveProfiles(profiles = {"test"})
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @DisplayName("회원이 정상적으로 회원가입한다. ")
    @Test
    void signupTest() {
        /* Given */
        MemberSignupRequest request = MemberFixture.JEONGUK.toMemberSignupRequest();

        /* When */
        /* Then */
        assertThatCode(() -> memberService.signup(request)).doesNotThrowAnyException();
    }
}
