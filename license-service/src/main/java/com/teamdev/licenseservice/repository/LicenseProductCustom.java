package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import com.teamdev.licenseservice.entity.LicenseProduct;

import java.util.List;

public interface LicenseProductCustom {

    LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName);

    List<LicenseWithProductCountDto> findAllLicensesWithProductCount();
}
