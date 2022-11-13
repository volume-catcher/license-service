package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Integer> {
}
