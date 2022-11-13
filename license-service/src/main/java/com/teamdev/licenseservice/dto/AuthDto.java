package com.teamdev.licenseservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthDto {

    private String token;

    @Builder
    public AuthDto(String token) {
        this.token = token;
    }
}
