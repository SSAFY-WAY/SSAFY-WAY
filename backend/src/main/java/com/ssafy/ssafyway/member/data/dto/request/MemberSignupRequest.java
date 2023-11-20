package com.ssafy.ssafyway.member.data.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MemberSignupRequest {
    private String name;
    private String email;
    private String password;
    private String validPassword;
    private String phoneNumber;

}
