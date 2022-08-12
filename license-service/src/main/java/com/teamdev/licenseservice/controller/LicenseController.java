package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/license")
public class LicenseController {

    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping
    public ResponseEntity<LicenseDto> createLicense() {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.createLicense());
    }

}
