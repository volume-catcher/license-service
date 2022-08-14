package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseProductDto;
import com.teamdev.licenseservice.dto.LicenseProductIsActivatedDto;
import com.teamdev.licenseservice.service.LicenseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/license-product")
public class LicenseProductController {

    private final LicenseProductService licenseProductService;

    @Autowired
    public LicenseProductController(LicenseProductService licenseProductService) {
        this.licenseProductService = licenseProductService;
    }

    @PostMapping
    public ResponseEntity<LicenseProductDto> createLicenseProduct(@Valid @RequestBody LicenseProductDto licenseProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseProductService.createLicenseProduct(licenseProductDto));
    }

    @PostMapping("/active")
    public ResponseEntity<LicenseProductDto> updateLicenseProductIsActivated(@Valid @RequestBody LicenseProductIsActivatedDto licenseProductIsActivatedDto) {
        return ResponseEntity.ok(licenseProductService.updateLicenseProductIsActivated(licenseProductIsActivatedDto));
    }
}
