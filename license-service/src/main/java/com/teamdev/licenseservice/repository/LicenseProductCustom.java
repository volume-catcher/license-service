package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseProduct;

public interface LicenseProductCustom {

    LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName);
}
