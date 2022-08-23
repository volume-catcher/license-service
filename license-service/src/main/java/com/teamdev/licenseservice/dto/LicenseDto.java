package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String licenseKey;

    @Builder
    public LicenseDto(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
