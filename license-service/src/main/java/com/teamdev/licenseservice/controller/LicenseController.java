package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseKeyDto;
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
    public LicenseKeyDto createLicense() {
        return licenseService.createLicense();
    }

    @GetMapping("/{id}")
    public List<LicenseKeyDto> getLicenseCreatedById(@PathVariable String id) {
        return licenseService.getLicenseCreatedById(id);
    }

    @GetMapping
    public List<LicenseKeyDto> getAllLicenses() {
        return licenseService.getAllLicenses();
    }
}
