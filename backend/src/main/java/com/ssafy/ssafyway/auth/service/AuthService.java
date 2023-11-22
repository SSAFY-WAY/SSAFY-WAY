package com.ssafy.ssafyway.auth.service;

import com.ssafy.ssafyway.auth.domain.AuthToken;
import com.ssafy.ssafyway.auth.domain.AuthTokenRepository;
import com.ssafy.ssafyway.auth.dto.request.LoginRequest;
import com.ssafy.ssafyway.auth.dto.response.TokenResponse;
import com.ssafy.ssafyway.auth.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthTokenRepository authTokenRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authentication = request.toAuthentication();
        Authentication authenticate =
                authenticationManagerBuilder.getObject().authenticate(authentication);

        TokenResponse response = tokenProvider.generateTokenResponse(authenticate);
        int memberId = Integer.parseInt(authenticate.getName());

        AuthToken token = AuthToken.builder().id(memberId).value(response.getRefreshToken()).build();
        authTokenRepository.save(token);
        return response;
    }
}
