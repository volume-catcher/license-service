package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseProductDto extends LicenseDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String productName;

    //TODO: 기본 값 초기화 문제 발생
    private boolean active;

    @PositiveOrZero
    private int numOfAuthAvailable;

    @NotNull
    @Future
    private LocalDateTime expireAt;

    public LicenseProductDto(String licenseKey, String productName, boolean active, int numOfAuthAvailable, LocalDateTime expireAt) {
        super(licenseKey);
        this.productName = productName;
        this.active = active;
        this.numOfAuthAvailable = numOfAuthAvailable;
        this.expireAt = expireAt;
    }

    public static LicenseProductDto from(LicenseProduct licenseProduct) {
        if (licenseProduct == null) return null;

        return LicenseProductDto.builder()
                .licenseKey(licenseProduct.getLicense().getKey())
                .productName(licenseProduct.getProduct().getName())
                .active(licenseProduct.getIsActivated())
                .numOfAuthAvailable(licenseProduct.getNumOfAuthAvailable())
                .expireAt(licenseProduct.getExpireAt())
                .build();
    }
}
