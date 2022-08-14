package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.LicenseResponseDto;
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
    public ResponseEntity<LicenseResponseDto> createLicense() {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.createLicense());
    }

    //TODO: 내 계정으로 발급한 라이선스 목록 확인한다.
    //TODO: admin 권한은 모든 라이선스 목록을 확인한다.
}
