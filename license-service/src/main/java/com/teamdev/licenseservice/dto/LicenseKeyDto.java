package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseKeyDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String key;

    @Builder
    public LicenseKeyDto(String key) {
        this.key = key;
    }

    public static LicenseKeyDto from(LicenseEntity licenseEntity) {
        if (licenseEntity == null) return null;

        return LicenseKeyDto.builder()
                .key(licenseEntity.getKey())
                .build();
    }

    public static LicenseKeyDto fromLicenseDto(LicenseDto licenseDto) {
        if (licenseDto == null) return null;

        return LicenseKeyDto.builder()
                .key(licenseDto.getKey())
                .build();
    }
}
