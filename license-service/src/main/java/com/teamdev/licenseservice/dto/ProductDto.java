package com.teamdev.licenseservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@SuperBuilder
public class ProductDto {

    @NotNull
    @Size(min = 3, max = 45)
    protected String name;

    public ProductDto(String name) {
        this.name = name;
    }
}
