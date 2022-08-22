package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository  extends JpaRepository<License, String> {
}
