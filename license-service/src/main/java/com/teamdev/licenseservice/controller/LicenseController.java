package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseKeyDto;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import com.teamdev.licenseservice.dto.ProductNameDto;
import com.teamdev.licenseservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/licenses")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LicenseKeyDto createLicense() {
        return licenseService.createLicense();
    }

    @GetMapping
    public List<LicenseWithProductCountDto> getAllLicenses(
            @PageableDefault(size = 5) Pageable pageable) {
        return licenseService.getAllLicenses(pageable);
    }

    @GetMapping("/{id}")
    public List<LicenseKeyDto> getLicenseCreatedById(@PathVariable String id) {
        return licenseService.getLicenseCreatedById(id);
    }

    @GetMapping("/{licenseKey}/products")
    public List<ProductNameDto> getProductsByLicenseKey(@PathVariable String licenseKey) {
        return licenseService.getProductsByLicenseKey(licenseKey);
    }
}
