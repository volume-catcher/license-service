package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProductDto {

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    private String id;

    @Builder
    public ProductDto(String name, String id) {
        this.name = name;
        this.id = id;
    }
}

