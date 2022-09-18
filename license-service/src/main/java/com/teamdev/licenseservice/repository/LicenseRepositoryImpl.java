package com.teamdev.licenseservice.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.teamdev.licenseservice.entity.QContractEntity.contractEntity;
import static com.teamdev.licenseservice.entity.QLicenseEntity.licenseEntity;

@Repository
@RequiredArgsConstructor
public class LicenseRepositoryImpl implements LicenseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<LicenseWithProductCountDto> findAllLicensesWithProductCountQ(Pageable pageable) {
        List<LicenseWithProductCountDto> results = jpaQueryFactory
                .select(Projections.constructor(LicenseWithProductCountDto.class,
                                licenseEntity.key,
                                ExpressionUtils.as(
                                        contractEntity.id.count(), "totalProductCount"
                                ),
                                ExpressionUtils.as(
                                        new CaseBuilder()
                                                .when(contractEntity.expireAt.before(
                                                        DateTimeExpression.currentTimestamp(LocalDateTime.class)))
                                                .then(1)
                                                .otherwise(0)
                                                .sum(),
                                        "expiredProductCount"
                                )
                        )
                )
                .from(contractEntity)
                .rightJoin(contractEntity.license, licenseEntity)
                .groupBy(licenseEntity.key)
                .orderBy(licenseEntity.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(results, pageable, getTotalCount()::fetchOne);
    }

    private JPAQuery<Long> getTotalCount() {
        return jpaQueryFactory
                .select(licenseEntity.key.count())
                .from(licenseEntity);
    }
}
