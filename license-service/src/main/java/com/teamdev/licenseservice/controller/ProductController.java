package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ProductResponseDto;
import com.teamdev.licenseservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponseDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductResponseDto> getLProductsCreatedByMe() {
        return productService.getProductsCreatedByMe();
    }

    @GetMapping("all")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
