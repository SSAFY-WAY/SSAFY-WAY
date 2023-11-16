package com.ssafy.ssafyway.member.domain.query;

import com.ssafy.ssafyway.member.data.cond.MemberExistBySignupCond;

public interface MemberRepositoryCustom {
    boolean existMemberBySignupCond(MemberExistBySignupCond cond);
}
