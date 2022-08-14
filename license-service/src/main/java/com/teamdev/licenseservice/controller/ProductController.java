package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ProductResponseDto;
import com.teamdev.licenseservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getLProductsCreatedByMe() {
        return ResponseEntity.ok(productService.getProductsCreatedByMe());
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
