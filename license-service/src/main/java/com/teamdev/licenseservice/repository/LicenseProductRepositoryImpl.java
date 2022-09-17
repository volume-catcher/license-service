package com.teamdev.licenseservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.teamdev.licenseservice.entity.QLicense.license;
import static com.teamdev.licenseservice.entity.QLicenseProduct.licenseProduct;
import static com.teamdev.licenseservice.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class LicenseProductRepositoryImpl implements LicenseProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName) {
        return jpaQueryFactory
                .select(licenseProduct)
                .from(licenseProduct)
                .join(licenseProduct.license, license)
                .on(license.key.eq(licenseKey))
                .join(licenseProduct.product, product)
                .on(product.name.eq(productName))
                .fetchOne();
    }

}
