package com.teamdev.licenseservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseDto {

    @NotNull
    @Size(min = 19, max = 19)
    protected String licenseKey;

    public LicenseDto(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
