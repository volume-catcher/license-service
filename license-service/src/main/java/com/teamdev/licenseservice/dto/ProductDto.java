package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductDto {

    @NotNull
    private String id;

    @NotNull
    @Size(min = 3, max = 45)
    protected String name;

    @Builder
    public ProductDto(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public static ProductDto from(ProductEntity productEntity) {
        if (productEntity == null) return null;

        return ProductDto.builder()
                .id(productEntity.getAccount().getId())
                .name(productEntity.getName())
                .build();
    }
}

