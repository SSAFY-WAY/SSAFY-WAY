package com.ssafy.ssafyway.global.fixture;

import com.ssafy.ssafyway.member.data.dto.request.MemberSignupRequest;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.domain.embbeded.Password;
import com.ssafy.ssafyway.member.domain.embbeded.PhoneNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum MemberFixture {
    SHINHAN(
            "김신한", "shinhan1234@gmail.com", "Password1234!@", "01012345678"),
    JEONGUK(
            "김정욱", "workju1124@gmail.com", "Password123!@", "01064817041");

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    MemberFixture(
            String name,
            String email,
            String password,
            String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public MemberSignupRequest toMemberSignupRequest() {
        return new MemberSignupRequest(name, email, password, password, phoneNumber);
    }

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(Email.from(email))
                .password(Password.of(password, password, new BCryptPasswordEncoder()))
                .phoneNumber(PhoneNumber.from(phoneNumber))
                .build();
    }
}
