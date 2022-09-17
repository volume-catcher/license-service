package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseProduct;

public interface LicenseProductRepositoryCustom {

    LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName);
}
