package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
