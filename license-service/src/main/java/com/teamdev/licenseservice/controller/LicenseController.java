package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseResponseDto;
import com.teamdev.licenseservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LicenseResponseDto createLicense() {
        return licenseService.createLicense();
    }

    @GetMapping("/{id}")
    public List<LicenseResponseDto> getLicenseCreatedById(@PathVariable String id) {
        return licenseService.getLicenseCreatedById(id);
    }

    @GetMapping
    public List<LicenseResponseDto> getAllLicenses() {
        return licenseService.getAllLicenses();
    }
}
