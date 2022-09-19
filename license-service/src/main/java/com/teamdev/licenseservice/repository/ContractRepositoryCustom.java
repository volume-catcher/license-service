package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.ContractEntity;

public interface ContractRepositoryCustom {

    ContractEntity findOneContractByLicenseKeyAndProductNameQ(String licenseKey, String productName);
}
