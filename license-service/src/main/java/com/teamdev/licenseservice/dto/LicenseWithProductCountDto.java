package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseWithProductCountDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String key;

    @NotNull
    @PositiveOrZero
    private Long totalProductCount;

    @NotNull
    @PositiveOrZero
    private Integer expiredProductCount;

    @Builder
    public LicenseWithProductCountDto(String key, Long totalProductCount, Integer expiredProductCount) {
        this.key = key;
        this.totalProductCount = totalProductCount;
        this.expiredProductCount = expiredProductCount;
    }

}