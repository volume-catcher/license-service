package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.Product;
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

    public static ProductNameDto from(Product product) {
        if (product == null) return null;

        return ProductNameDto.builder()
                .name(product.getName())
                .build();
    }

    public static ProductNameDto fromProductDto(ProductDto productDto) {
        if (productDto == null) return null;

        return ProductNameDto.builder()
                .name(productDto.getName())
                .build();
    }
}
