package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.dto.LicenseResponseDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.ForbiddenException;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.license.SerialNumber;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.LicenseRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public LicenseResponseDto createLicense() {
        String licenseKey = SerialNumber.getSerialNumber();
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

        while (licenseRepository.findByKey(licenseKey).isPresent()) {
            licenseKey = SerialNumber.getSerialNumber();
        }

        LicenseResponseDto licenseResponseDto = LicenseResponseDto.builder()
                .key(licenseKey)
                .accountId(id)
                .build();
        
        saveLicenseWithValidAccount(licenseResponseDto);

        return licenseResponseDto;
    }

    public List<LicenseResponseDto> getLicenseCreatedById(String id) {
        if (!SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)).equals(id)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return licenseRepository.findAllByAccountId(id).stream().map(LicenseResponseDto::from).collect(Collectors.toList());
    }

    public List<LicenseResponseDto> getAllLicenses() {
        return licenseRepository.findAll().stream().map(LicenseResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public License saveLicenseWithValidAccount(LicenseResponseDto licenseResponseDto) {
        Optional<Account> account = accountRepository.findById(licenseResponseDto.getAccountId());

        if (account.isEmpty()) {
            throw new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND);
        }

        License license = License.builder()
                .key(licenseResponseDto.getKey())
                .account(account.get())
                .build();

        return licenseRepository.save(license);
    }

    public License getLicenseByKey(LicenseDto licenseDto) {
        Optional<License> license = licenseRepository.findByKey(licenseDto.getLicenseKey());

        if (license.isEmpty()) {
            throw new NotFoundException(ErrorMessage.LICENSE_NOT_FOUND);
        }

        return license.get();
    }

}
