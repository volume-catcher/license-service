package com.teamdev.licenseservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LicenseProductDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String licenseKey;

    @NotNull
    @Size(min = 3, max = 45)
    private String productName;

    @NotNull
    private Boolean isActivated;

    @NotNull
    @PositiveOrZero
    private Integer numOfAuthAvailable;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime expireAt;

    @Builder
    public LicenseProductDto(String licenseKey,
                             String productName,
                             Boolean isActivated,
                             Integer numOfAuthAvailable,
                             LocalDateTime expireAt) {
        this.licenseKey = licenseKey;
        this.productName = productName;
        this.isActivated = isActivated;
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
