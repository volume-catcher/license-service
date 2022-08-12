package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ReqProductDto;
import com.teamdev.licenseservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ReqProductDto reqProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(reqProductDto));
    }
}
