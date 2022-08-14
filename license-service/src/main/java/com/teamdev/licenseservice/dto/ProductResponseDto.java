package com.teamdev.licenseservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@SuperBuilder
public class ProductResponseDto extends ProductDto {

    @NotNull
    private String id;

    public ProductResponseDto(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public static ProductResponseDto from(ProductResponseDto productResponseDto) {
        if (productResponseDto == null) return null;

        return ProductResponseDto.builder()
                .id(productResponseDto.getId())
                .name(productResponseDto.getName())
                .build();
    }
}

