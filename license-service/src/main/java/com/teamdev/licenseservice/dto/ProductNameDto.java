package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductNameDto {

    @NotNull
    @Size(min = 3, max = 45)
    protected String name;

    @Builder
    public ProductNameDto(String name) {
        this.name = name;
    }

    public static ProductNameDto from(ProductEntity productEntity) {
        if (productEntity == null) return null;

        return ProductNameDto.builder()
                .name(productEntity.getName())
                .build();
    }

    public static ProductNameDto from(ProductDto productDto) {
        if (productDto == null) return null;

        return ProductNameDto.builder()
                .name(productDto.getName())
                .build();
    }
}
