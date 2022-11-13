package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.SignInDto;
import com.teamdev.licenseservice.dto.AuthDto;
import com.teamdev.licenseservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthDto signIn(SignInDto signInDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInDto.getId(), signInDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String jwt = tokenProvider.createToken(authentication);

        return new AuthDto(jwt);
    }

}
