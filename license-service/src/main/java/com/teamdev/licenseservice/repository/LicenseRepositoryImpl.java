package com.teamdev.licenseservice.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.dto.LicenseWithProductCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.teamdev.licenseservice.entity.QContractEntity.contractEntity;
import static com.teamdev.licenseservice.entity.QLicenseEntity.licenseEntity;

@Repository
@RequiredArgsConstructor
public class LicenseRepositoryImpl implements LicenseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<LicenseWithProductCountDto> findAllLicenseWithProductCountQ(Pageable pageable) {
        List<LicenseWithProductCountDto> results = queryFindLicenseWithProductCount(pageable).fetch();
        return PageableExecutionUtils.getPage(results, pageable, queryGetCount()::fetchOne);
    }

    @Override
    public Page<LicenseWithProductCountDto> findAllLicenseWithProductCountQ(String searchWord, Pageable pageable) {
        List<LicenseWithProductCountDto> results = queryFindLicenseWithProductCount(pageable)
                .where(licenseEntity.key.contains(searchWord))
                .fetch();

        return PageableExecutionUtils.getPage(results, pageable,
                queryGetCount().where(licenseEntity.key.contains(searchWord))::fetchOne);
    }

    private JPAQuery<LicenseWithProductCountDto> queryFindLicenseWithProductCount(Pageable pageable) {
        NumberPath<Long> aliasTotalProductCount = Expressions.numberPath(Long.class, "totalProductCount");
        NumberPath<Long> aliasExpiredProductCount = Expressions.numberPath(Long.class, "expiredProductCount");

        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(pageable,
                licenseEntity.createAt,
                new ArrayList<>(Arrays.asList(aliasTotalProductCount, aliasExpiredProductCount)));

        return jpaQueryFactory
                .select(Projections.constructor(LicenseWithProductCountDto.class,
                                licenseEntity.key,
                                contractEntity.id.count().as(aliasTotalProductCount),
                                new CaseBuilder()
                                        .when(contractEntity.expireAt.before(
                                                DateTimeExpression.currentTimestamp(LocalDateTime.class)))
                                        .then(1)
                                        .otherwise(0)
                                        .sum()
                                        .longValue()
                                        .as(aliasExpiredProductCount)
                        )
                )
                .from(contractEntity)
                .rightJoin(contractEntity.license, licenseEntity)
                .groupBy(licenseEntity.key)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    private JPAQuery<Long> queryGetCount() {
        return jpaQueryFactory
                .select(licenseEntity.key.count())
                .from(licenseEntity);
    }

    private OrderSpecifier<?> getOrderSpecifier(Pageable pageable, ComparableExpressionBase<?> defaultComparator, List<ComparableExpressionBase<?>> aliases) {
        String sortProperty = defaultComparator.toString();
        Sort.Direction sortDirection = Sort.Direction.DESC;

        for (Sort.Order order: pageable.getSort()) {
            sortProperty = order.getProperty();
            sortDirection = order.getDirection();
        }

        ComparableExpressionBase<?> comparableExpression = defaultComparator;

        for (ComparableExpressionBase<?> alias: aliases) {
            if (sortProperty.equals(alias.toString())) {
                comparableExpression = alias;
                break;
            }
        }

        OrderSpecifier<?> orderSpecifier = comparableExpression.desc();;

        if (sortDirection.isAscending()) {
            orderSpecifier = comparableExpression.asc();
        }

        return orderSpecifier;
    }
}
