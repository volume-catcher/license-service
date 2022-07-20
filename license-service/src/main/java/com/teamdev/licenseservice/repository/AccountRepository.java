package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
