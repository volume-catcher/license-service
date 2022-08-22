package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseProductRepository extends JpaRepository<LicenseProduct, Integer> {
}
