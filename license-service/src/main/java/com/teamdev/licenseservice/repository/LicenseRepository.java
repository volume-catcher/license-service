package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<LicenseEntity, String>, LicenseRepositoryCustom {

    Optional<LicenseEntity> findByKey(String key);
    List<LicenseEntity> findAllByAccountId(String id);
}
