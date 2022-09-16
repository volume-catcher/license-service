package com.teamdev.licenseservice.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import com.teamdev.licenseservice.entity.LicenseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.teamdev.licenseservice.entity.QLicenseProduct.licenseProduct;
import static com.teamdev.licenseservice.entity.QLicense.license;
import static com.teamdev.licenseservice.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class LicenseProductRepositoryImpl implements LicenseProductCustom {

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

    public List<LicenseWithProductCountDto> findAllLicensesWithProductCount() {
        return jpaQueryFactory
                .select(Projections.constructor(LicenseWithProductCountDto.class,
                        licenseProduct.license.key,
                        ExpressionUtils.as(
                            licenseProduct.license.key.count(), "totalProductCount"
                        ),
                        ExpressionUtils.as(
                            new CaseBuilder()
                                    .when(licenseProduct.expireAt.before(DateTimeExpression.currentTimestamp(LocalDateTime.class)))
                                    .then(1)
                                    .otherwise(0)
                                    .sum(),
                            "expiredProductCount"
                        )
                    )
                )
                .from(licenseProduct)
                .groupBy(licenseProduct.license.key)
                .fetch();
    }
}
