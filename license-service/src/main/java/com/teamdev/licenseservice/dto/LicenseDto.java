package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseEntity;
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
    private String key;

    @NotNull
    @Size(min = 3, max = 20)
    private String accountId;

    @Builder
    public LicenseDto(String key, String accountId) {
        this.key = key;
        this.accountId = accountId;
    }

    public static LicenseDto from(LicenseEntity licenseEntity) {
        if (licenseEntity == null) return null;

        return LicenseDto.builder()
                .key(licenseEntity.getKey())
                .accountId(licenseEntity.getAccount().getId())
                .build();
    }
}
