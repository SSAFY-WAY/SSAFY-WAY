package com.ssafy.ssafyway.member.data.cond;

import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.domain.embbeded.PhoneNumber;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class MemberExistBySignupCond {
    private Email email;
    private PhoneNumber phoneNumber;

    public static MemberExistBySignupCond of(Email email, PhoneNumber phoneNumber) {
        return MemberExistBySignupCond.builder()
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
