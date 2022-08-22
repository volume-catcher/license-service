package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseProductDto;
import com.teamdev.licenseservice.dto.LicenseProductIsActivatedDto;
import com.teamdev.licenseservice.service.LicenseProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/license-product")
@RequiredArgsConstructor
public class LicenseProductController {

    private final LicenseProductService licenseProductService;

    @PostMapping
    public ResponseEntity<LicenseProductDto> createLicenseProduct(@Valid @RequestBody LicenseProductDto licenseProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseProductService.createLicenseProduct(licenseProductDto));
    }

    @PostMapping("/active")
    public ResponseEntity<LicenseProductDto> updateLicenseProductIsActivated(@Valid @RequestBody LicenseProductIsActivatedDto licenseProductIsActivatedDto) {
        return ResponseEntity.ok(licenseProductService.updateLicenseProductIsActivated(licenseProductIsActivatedDto));
    }

    @GetMapping("all")
    public ResponseEntity<List<LicenseProductDto>> getAllLicenseProducts() {
        return ResponseEntity.ok(licenseProductService.getAllLicenseProducts());
    }
}
