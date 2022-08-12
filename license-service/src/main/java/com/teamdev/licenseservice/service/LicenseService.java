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

    private final LicenseRepository licenseRepository;
    private final AccountRepository accountRepository;
    private final Logger logger = LoggerFactory.getLogger(LicenseService.class);

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
        saveLicense(licenseDto);

        logger.debug("라이선스를 발급하였습니다, id: {}, key: {}", id, licenseKey);

        return licenseDto;
    }

    @Transactional
    public License saveLicense(LicenseDto licenseDto) {
        Optional<License> preLicense = licenseRepository.findByKey(licenseDto.getKey());
        if (preLicense.isPresent()) {
            return preLicense.get();
        }

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
