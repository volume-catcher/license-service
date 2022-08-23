package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.SignInDto;
import com.teamdev.licenseservice.dto.TokenDto;
import com.teamdev.licenseservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public TokenDto signIn(@Valid @RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

}
