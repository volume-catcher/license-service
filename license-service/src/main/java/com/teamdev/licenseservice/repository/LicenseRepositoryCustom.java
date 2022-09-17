package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LicenseRepositoryCustom {

    List<LicenseWithProductCountDto> findAllLicensesWithProductCountQ(Pageable pageable);
}
