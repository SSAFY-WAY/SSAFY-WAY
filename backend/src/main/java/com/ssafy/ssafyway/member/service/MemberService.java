package com.ssafy.ssafyway.member.service;

import com.ssafy.ssafyway.member.data.dto.request.MemberSignupRequest;
import com.ssafy.ssafyway.member.domain.Member;
import com.ssafy.ssafyway.member.domain.MemberRepository;
import com.ssafy.ssafyway.member.exception.MemberErrorCode;
import com.ssafy.ssafyway.member.exception.MemberException;
import com.ssafy.ssafyway.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public ResponseEntity<Void> signup(MemberSignupRequest request) {
        memberRepository.save(memberMapper.toMember(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public Member findById(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.ERROR_CLIENT_WITH_MEMBER_IS_NOT_EXISTED));
    }
}
