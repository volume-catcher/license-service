package com.teamdev.licenseservice.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.dto.LicenseProductNumDto;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.entity.QLicense;
import com.teamdev.licenseservice.entity.QLicenseProduct;
import com.teamdev.licenseservice.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LicenseProductRepositoryImpl implements LicenseProductCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QLicenseProduct licenseProduct = QLicenseProduct.licenseProduct;

    @Override
    public LicenseProduct findOneLicenseProductByLicenseKeyAndProductNameQ(String licenseKey, String productName) {
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

    public List<LicenseProductNumDto> countAllLicenseProduct() {
        return jpaQueryFactory
                .select(Projections.constructor(LicenseProductNumDto.class,
                        licenseProduct.license.key,
                        ExpressionUtils.as(
                            licenseProduct.license.key.count(), "totalProductNum"
                        ),
                        ExpressionUtils.as(
                            new CaseBuilder()
                                    .when(licenseProduct.expireAt.before(DateTimeExpression.currentTimestamp(LocalDateTime.class)))
                                    .then(1)
                                    .otherwise(0)
                                    .sum(),
                            "expiredProductNum"
                        )
                    )
                )
                .from(licenseProduct)
                .groupBy(licenseProduct.license.key)
                .fetch();
    }
}
