package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseProduct;

import java.util.List;

public interface LicenseProductCustom {

    List<LicenseProduct> findLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName);
}
