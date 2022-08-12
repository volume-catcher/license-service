package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenseRepository  extends JpaRepository<License, String> {

    Optional<License> findByKey(String key);
}
