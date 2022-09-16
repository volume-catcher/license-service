package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.ProductNameDto;
import com.teamdev.licenseservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductNameDto createProduct(@Valid @RequestBody ProductNameDto productNameDto) {
        return productService.createProduct(productNameDto);
    }

    @GetMapping("/{id}")
    public List<ProductNameDto> getProductsCreatedById(@PathVariable String id) {
        return productService.getProductsCreatedById(id);
    }

    @GetMapping
    public List<ProductNameDto> getAllProducts() {
        return productService.getAllProducts();
    }

}
