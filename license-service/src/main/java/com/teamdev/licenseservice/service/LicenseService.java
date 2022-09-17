package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.dto.LicenseKeyDto;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import com.teamdev.licenseservice.dto.ProductNameDto;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.ForbiddenException;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.license.SerialNumber;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.LicenseProductRepository;
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
    private final LicenseProductRepository licenseProductRepository;

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

    public List<LicenseKeyDto> getLicenseCreatedById(String id) {
        if (!SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)).equals(id)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return licenseRepository.findAllByAccountId(id).stream().map(LicenseKeyDto::from).collect(Collectors.toList());
    }

    public List<LicenseWithProductCountDto> getAllLicenses(Pageable pageable) {
        return licenseRepository.findAllLicensesWithProductCountQ(pageable);
    }

    public List<ProductNameDto> getProductsByLicenseKey(String licenseKey) {
        return licenseProductRepository
                .findLicenseProductWithProductByLicenseKey(licenseKey)
                .stream()
                .map(LicenseProduct::getProduct)
                .map(ProductNameDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public License saveLicenseWithValidAccount(LicenseDto licenseDto) {
        return accountRepository.findById(licenseDto.getAccountId())
                .map(account -> {
                    License license = License.builder()
                            .key(licenseDto.getKey())
                            .account(account)
                            .build();
                    return licenseRepository.save(license);
                })
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }

    public License getLicenseByKey(LicenseKeyDto licenseKeyDto) {
        return licenseRepository.findByKey(licenseKeyDto.getKey())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LICENSE_NOT_FOUND));
    }

}
