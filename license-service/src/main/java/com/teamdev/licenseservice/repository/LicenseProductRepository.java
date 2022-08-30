package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseProductRepository extends JpaRepository<LicenseProduct, Integer>, LicenseProductCustom {

    @EntityGraph(attributePaths = "product")
    List<LicenseProduct> findLicenseProductWithProductByLicenseKey(String licenseKey);

}
