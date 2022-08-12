package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import com.teamdev.licenseservice.license.SerialNumber;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.LicenseRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public LicenseDto createLicense() {
        String licenseKey = SerialNumber.getSerialNumber();
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));

        while (licenseRepository.findByKey(licenseKey).isPresent()) {
            licenseKey = SerialNumber.getSerialNumber();
        }

        LicenseDto licenseDto = LicenseDto.builder()
                .key(licenseKey)
                .id(id)
                .build();
        
        saveLicenseWithValidAccount(licenseDto);
        
        logger.debug("라이선스를 발급하였습니다, id: {}, 라이선스키: {}", id, licenseKey);

        return licenseDto;
    }

    @Transactional
    public License saveLicenseWithValidAccount(LicenseDto licenseDto) {
        Optional<Account> account = accountRepository.findById(licenseDto.getId());

        if (account.isEmpty()) {
            throw new NotFoundAccountException(licenseDto.getId());
        }

        License license = License.builder()
                .key(licenseDto.getKey())
                .account(account.get())
                .build();

        return licenseRepository.save(license);
    }
}
