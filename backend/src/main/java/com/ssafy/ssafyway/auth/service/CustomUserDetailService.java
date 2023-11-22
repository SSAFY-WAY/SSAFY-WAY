package com.ssafy.ssafyway.auth.service;

import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.domain.MemberRepository;
import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.exception.MemberErrorCode;
import com.ssafy.ssafyway.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository
                .findByEmail(Email.from(email))
                .map(this::createUserDetails)
                .orElseThrow(
                        () -> new MemberException(MemberErrorCode.ERROR_CLIENT_WITH_MEMBER_IS_NOT_EXISTED));
    }

    private UserDetails createUserDetails(Member member) {
        SimpleGrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(member.getRole().getValue());
        return new User(
                String.valueOf(member.getId()),
                member.getEncryptedPassword(),
                Collections.singleton(grantedAuthority));
    }
}
