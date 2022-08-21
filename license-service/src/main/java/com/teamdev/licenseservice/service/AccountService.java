package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.AccountDto;
import com.teamdev.licenseservice.dto.RoleDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.Role;
import com.teamdev.licenseservice.exception.DuplicatedException;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AccountDto signUp(AccountDto accountDto) {
        if (accountRepository.findById(accountDto.getId()).isPresent()) {
            throw new DuplicatedException(ErrorMessage.ACCOUNT_DUPLICATED);
        }

        Role role = roleService.getOrSaveRole(RoleDto.builder()
                .name("ROLE_USER")
                .build());

        Account account = Account.builder()
                .id(accountDto.getId())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        return AccountDto.from(accountRepository.save(account));
    }

    public AccountDto getAccountWithRoles(String id) {
        return AccountDto.from(accountRepository.findOneWithRolesById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }

    public AccountDto getMyAccountWithRoles() {
        return AccountDto.from(SecurityUtil.getCurrentId().flatMap(accountRepository::findOneWithRolesById).orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }
}
