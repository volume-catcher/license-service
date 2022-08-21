package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseProductDto extends LicenseProductIsActivatedDto {

    @NotNull
    @PositiveOrZero
    private Integer numOfAuthAvailable;

    @NotNull
    @Future
    private LocalDateTime expireAt;

    public LicenseProductDto(String licenseKey, String productName, Boolean isActivated, Integer numOfAuthAvailable, LocalDateTime expireAt) {
        super(licenseKey, productName, isActivated);
        this.numOfAuthAvailable = numOfAuthAvailable;
        this.expireAt = expireAt;
    }

    public static LicenseProductDto from(LicenseProduct licenseProduct) {
        if (licenseProduct == null) return null;

        return LicenseProductDto.builder()
                .licenseKey(licenseProduct.getLicense().getKey())
                .productName(licenseProduct.getProduct().getName())
                .isActivated(licenseProduct.getIsActivated())
                .numOfAuthAvailable(licenseProduct.getNumOfAuthAvailable())
                .expireAt(licenseProduct.getExpireAt())
                .build();
    }
}
