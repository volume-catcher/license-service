package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.dto.LicenseResponseDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import com.teamdev.licenseservice.exception.NotFoundLicenseException;
import com.teamdev.licenseservice.license.SerialNumber;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.LicenseRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LicenseService {

    private final Logger logger = LoggerFactory.getLogger(LicenseService.class);
    
    private final LicenseRepository licenseRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository, AccountRepository accountRepository) {
        this.licenseRepository = licenseRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public LicenseResponseDto createLicense() {
        String licenseKey = SerialNumber.getSerialNumber();
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));

        while (licenseRepository.findByKey(licenseKey).isPresent()) {
            licenseKey = SerialNumber.getSerialNumber();
        }

        LicenseResponseDto licenseResponseDto = LicenseResponseDto.builder()
                .key(licenseKey)
                .accountId(id)
                .build();
        
        saveLicenseWithValidAccount(licenseResponseDto);
        
        logger.debug("라이선스를 발급하였습니다, id: {}, 라이선스키: {}", id, licenseKey);

        return licenseResponseDto;
    }

    public List<LicenseResponseDto> getLicensesCreatedByMe() {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));
        return licenseRepository.findAllByAccountId(id).stream().map(LicenseResponseDto::from).collect(Collectors.toList());
    }

    public List<LicenseResponseDto> getAllLicenses() {
        return licenseRepository.findAll().stream().map(LicenseResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public License saveLicenseWithValidAccount(LicenseResponseDto licenseResponseDto) {
        Optional<Account> account = accountRepository.findById(licenseResponseDto.getAccountId());

        if (account.isEmpty()) {
            throw new NotFoundAccountException(licenseResponseDto.getAccountId());
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
            throw new NotFoundLicenseException(licenseDto.getLicenseKey());
        }

        return license.get();
    }

}
