package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ReqProductDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @Builder
    public ReqProductDto(String name) {
        this.name = name;
    }
}
