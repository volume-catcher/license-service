package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseProductNumDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String key;

    @NotNull
    @PositiveOrZero
    private Long totalProductNum;

    @NotNull
    @PositiveOrZero
    private Integer expiredProductNum;

    @Builder
    public LicenseProductNumDto(String key, Long totalProductNum, Integer expiredProductNum) {
        this.key = key;
        this.totalProductNum = totalProductNum;
        this.expiredProductNum = expiredProductNum;
    }

}