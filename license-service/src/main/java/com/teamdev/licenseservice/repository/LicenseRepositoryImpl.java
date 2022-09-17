package com.teamdev.licenseservice.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.teamdev.licenseservice.entity.QLicense.license;
import static com.teamdev.licenseservice.entity.QLicenseProduct.licenseProduct;

@Repository
@RequiredArgsConstructor
public class LicenseRepositoryImpl implements LicenseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LicenseWithProductCountDto> findAllLicensesWithProductCountQ(Pageable pageable) {
        return  jpaQueryFactory
                .select(Projections.constructor(LicenseWithProductCountDto.class,
                                license.key,
                                ExpressionUtils.as(
                                        licenseProduct.id.count(), "totalProductCount"
                                ),
                                ExpressionUtils.as(
                                        new CaseBuilder()
                                                .when(licenseProduct.expireAt.before(
                                                        DateTimeExpression.currentTimestamp(LocalDateTime.class)))
                                                .then(1)
                                                .otherwise(0)
                                                .sum(),
                                        "expiredProductCount"
                                )
                        )
                )
                .from(licenseProduct)
                .rightJoin(licenseProduct.license, license)
                .groupBy(license.key)
                .orderBy(license.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
