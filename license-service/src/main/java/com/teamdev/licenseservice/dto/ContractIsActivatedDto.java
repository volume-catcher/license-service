package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.ContractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ContractIsActivatedDto {

    @NotNull
    @Size(min = 19, max = 19)
    private String licenseKey;

    @NotNull
    @Size(min = 3, max = 45)
    private String productName;

    @NotNull
    private Boolean isActivated;

    @Builder
    public ContractIsActivatedDto(String licenseKey, String productName, Boolean isActivated) {
        this.licenseKey = licenseKey;
        this.productName = productName;
        this.isActivated = isActivated;
    }

    public static ContractIsActivatedDto from(ContractEntity contractEntity) {
        if (contractEntity == null) return null;

        return ContractIsActivatedDto.builder()
                .licenseKey(contractEntity.getLicense().getKey())
                .productName(contractEntity.getProduct().getName())
                .isActivated(contractEntity.getIsActivated())
                .build();
    }
}
