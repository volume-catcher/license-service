package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.*;
import com.teamdev.licenseservice.entity.LicenseEntity;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.license.SerialNumber;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.ContractRepository;
import com.teamdev.licenseservice.repository.LicenseRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final AccountRepository accountRepository;
    private final ContractRepository contractRepository;

    @Transactional
    public LicenseKeyDto createLicense() {
        String licenseKey = SerialNumber.getSerialNumber();
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

        while (licenseRepository.findByKey(licenseKey).isPresent()) {
            licenseKey = SerialNumber.getSerialNumber();
        }

        LicenseDto licenseDto = LicenseDto.builder()
                .key(licenseKey)
                .accountId(id)
                .build();

        saveLicenseWithValidAccount(licenseDto);

        return LicenseKeyDto.fromLicenseDto(licenseDto);
    }

    public PageDto<LicenseWithProductCountDto> getAllLicense(Pageable pageable) {
        return PageDto.from(licenseRepository
                .findAllLicenseWithProductCountQ(pageable));
    }

    public PageDto<LicenseWithProductCountDto> getAllLicense(String searchWord, Pageable pageable) {
        return PageDto.from(licenseRepository
                .findAllLicenseWithProductCountQ(searchWord, pageable));
    }

    public List<ContractDto> getContractsByLicenseKey(String licenseKey) {
        return contractRepository
                .findContractByLicenseKey(licenseKey)
                .stream()
                .map(ContractDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public LicenseEntity saveLicenseWithValidAccount(LicenseDto licenseDto) {
        return accountRepository.findById(licenseDto.getAccountId())
                .map(account -> {
                    LicenseEntity licenseEntity = LicenseEntity.builder()
                            .key(licenseDto.getKey())
                            .account(account)
                            .build();
                    return licenseRepository.save(licenseEntity);
                })
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }

    public LicenseEntity getLicenseByKey(LicenseKeyDto licenseKeyDto) {
        return licenseRepository.findByKey(licenseKeyDto.getKey())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LICENSE_NOT_FOUND));
    }

}
