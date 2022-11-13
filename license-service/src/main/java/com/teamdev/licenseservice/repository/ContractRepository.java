package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Integer>, ContractRepositoryCustom {

    List<ContractEntity> findContractByLicenseKey(String licenseKey);

}
