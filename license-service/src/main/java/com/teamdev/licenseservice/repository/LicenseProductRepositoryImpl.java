package com.teamdev.licenseservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.entity.QLicense;
import com.teamdev.licenseservice.entity.QLicenseProduct;
import com.teamdev.licenseservice.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LicenseProductRepositoryImpl implements LicenseProductCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName) {
        QLicenseProduct licenseProduct = QLicenseProduct.licenseProduct;
        QLicense license = QLicense.license;
        QProduct product = QProduct.product;

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
