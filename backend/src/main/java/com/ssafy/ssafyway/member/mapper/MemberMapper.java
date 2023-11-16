package com.ssafy.ssafyway.member.mapper;

import com.ssafy.ssafyway.member.data.dto.request.MemberSignupRequest;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.domain.embbeded.Password;
import com.ssafy.ssafyway.member.domain.embbeded.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberMapper {
    private final PasswordEncoder passwordEncoder;

    public Member toMember(MemberSignupRequest request) {
        Email email = Email.from(request.getEmail());
        Password password = Password.of(request.getPassword(), request.getValidPassword(), passwordEncoder);
        PhoneNumber phoneNumber = PhoneNumber.from(request.getPhoneNumber());

        return Member.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
