package com.teamdev.licenseservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamdev.licenseservice.entity.ContractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.teamdev.licenseservice.entity.QLicenseEntity.licenseEntity;
import static com.teamdev.licenseservice.entity.QContractEntity.contractEntity;
import static com.teamdev.licenseservice.entity.QProductEntity.productEntity;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ContractEntity findOneContractByLicenseKeyAndProductNameQ(String licenseKey, String productName) {
        return jpaQueryFactory
                .select(contractEntity)
                .from(contractEntity)
                .join(contractEntity.license, licenseEntity)
                .on(licenseEntity.key.eq(licenseKey))
                .join(contractEntity.product, productEntity)
                .on(productEntity.name.eq(productName))
                .fetchOne();
    }

}