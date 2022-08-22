package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Integer> {
}
