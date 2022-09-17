package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<AccountEntity> findOneWithRolesById(String id);
}
