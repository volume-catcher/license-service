package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LicenseProductIsActivatedDto {

    @NotNull
    @Size(min = 19, max = 19)
    protected String licenseKey;

    @NotNull
    @Size(min = 3, max = 45)
    private String productName;

    @NotNull
    private boolean active;

    @Builder
    public LicenseProductIsActivatedDto(String licenseKey, String productName, boolean active) {
        this.licenseKey = licenseKey;
        this.productName = productName;
        this.active = active;
    }

    public static LicenseProductIsActivatedDto from(LicenseProduct licenseProduct) {
        if (licenseProduct == null) return null;

        return LicenseProductIsActivatedDto.builder()
                .licenseKey(licenseProduct.getLicense().getKey())
                .productName(licenseProduct.getProduct().getName())
                .active(licenseProduct.getIsActivated())
                .build();
    }
}
