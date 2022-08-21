package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseProductIsActivatedDto extends LicenseDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String productName;

    @NotNull
    private Boolean isActivated;

    public LicenseProductIsActivatedDto(String licenseKey, String productName, Boolean isActivated) {
        this.licenseKey = licenseKey;
        this.productName = productName;
        this.isActivated = isActivated;
    }

    public static LicenseProductIsActivatedDto from(LicenseProduct licenseProduct) {
        if (licenseProduct == null) return null;

        return LicenseProductIsActivatedDto.builder()
                .licenseKey(licenseProduct.getLicense().getKey())
                .productName(licenseProduct.getProduct().getName())
                .isActivated(licenseProduct.getIsActivated())
                .build();
    }
}
