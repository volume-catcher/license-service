package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseResponseDto;
import com.teamdev.licenseservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping
    public ResponseEntity<LicenseResponseDto> createLicense() {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.createLicense());
    }

    @GetMapping
    public ResponseEntity<List<LicenseResponseDto>> getLicensesCreatedByMe() {
        return ResponseEntity.ok(licenseService.getLicensesCreatedByMe());
    }

    @GetMapping("all")
    public ResponseEntity<List<LicenseResponseDto>> getAllLicenses() {
        return ResponseEntity.ok(licenseService.getAllLicenses());
    }
}
