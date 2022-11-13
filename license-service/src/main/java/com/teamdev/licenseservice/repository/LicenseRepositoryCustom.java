package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LicenseRepositoryCustom {

    Page<LicenseWithProductCountDto> findAllLicenseWithProductCountQ(Pageable pageable);
    Page<LicenseWithProductCountDto> findAllLicenseWithProductCountQ(String searchWord, Pageable pageable);
}
