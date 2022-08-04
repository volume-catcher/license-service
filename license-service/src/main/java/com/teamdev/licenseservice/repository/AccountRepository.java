package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<Account> findOneWithRolesById(String id);
}
