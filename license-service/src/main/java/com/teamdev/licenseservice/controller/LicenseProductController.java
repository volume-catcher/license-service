package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseProductDto;
import com.teamdev.licenseservice.dto.LicenseProductIsActivatedDto;
import com.teamdev.licenseservice.service.LicenseProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/license-product")
@RequiredArgsConstructor
public class LicenseProductController {

    private final LicenseProductService licenseProductService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LicenseProductDto createLicenseProduct(@Valid @RequestBody LicenseProductDto licenseProductDto) {
        return licenseProductService.createLicenseProduct(licenseProductDto);
    }

    @PostMapping("/active")
    public LicenseProductDto updateLicenseProductIsActivated(@Valid @RequestBody LicenseProductIsActivatedDto licenseProductIsActivatedDto) {
        return licenseProductService.updateLicenseProductIsActivated(licenseProductIsActivatedDto);
    }

    @GetMapping("all")
    public List<LicenseProductDto> getAllLicenseProducts() {
        return licenseProductService.getAllLicenseProducts();
    }
}
