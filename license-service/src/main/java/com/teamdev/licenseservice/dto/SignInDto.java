package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignInDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String id;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @Builder
    public SignInDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}