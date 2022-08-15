package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.License;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseResponseDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String key;

    @NotNull
    @Size(min = 3, max = 20)
    private String accountId;

    @Builder
    public LicenseResponseDto(String key, String accountId) {
        this.key = key;
        this.accountId = accountId;
    }

    public static LicenseResponseDto from(License license) {
        if (license == null) return null;

        return LicenseResponseDto.builder()
                .key(license.getKey())
                .accountId(license.getAccount().getId())
                .build();
    }
}