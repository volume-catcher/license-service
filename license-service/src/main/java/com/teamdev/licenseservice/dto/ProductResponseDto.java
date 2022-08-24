package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.Product;
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

    public static ProductResponseDto from(Product product) {
        if (product == null) return null;

        return ProductResponseDto.builder()
                .id(product.getAccount().getId())
                .name(product.getName())
                .build();
    }
}

