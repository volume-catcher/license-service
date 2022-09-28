package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.PageDto;
import com.teamdev.licenseservice.dto.ProductNameDto;
import com.teamdev.licenseservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public PageDto<ProductNameDto> getAllProduct(
            @PageableDefault(size = 5, sort = "createAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return productService.getAllProduct(pageable);
    }

    @GetMapping("/{searchWord}")
    public PageDto<ProductNameDto> getAllProduct(
            @PathVariable String searchWord,
            @PageableDefault(size = 5, sort = "createAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return productService.getAllProduct(searchWord, pageable);
    }

    @GetMapping("/names")
    public List<ProductNameDto> getAllProduct() {
        return productService.getAllProduct();
    }

}
