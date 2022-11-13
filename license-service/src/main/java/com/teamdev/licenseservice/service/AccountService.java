package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.AccountDto;
import com.teamdev.licenseservice.dto.RoleDto;
import com.teamdev.licenseservice.entity.AccountEntity;
import com.teamdev.licenseservice.entity.RoleEntity;
import com.teamdev.licenseservice.exception.DuplicatedException;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AccountDto signUp(AccountDto accountDto) {
        if (accountRepository.findById(accountDto.getId()).isPresent()) {
            throw new DuplicatedException(ErrorMessage.ACCOUNT_DUPLICATED);
        }

        RoleEntity roleEntity = roleService.getOrSaveRole(RoleDto.builder()
                .name("ROLE_USER")
                .build());

        AccountEntity accountEntity = AccountEntity.builder()
                .id(accountDto.getId())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .roles(Collections.singleton(roleEntity))
                .build();

        return AccountDto.from(accountRepository.save(accountEntity));
    }

    public AccountDto getAccountWithRoles(String id) {
        return AccountDto.from(accountRepository.findOneWithRolesById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }

    public AccountDto getMyAccountWithRoles() {
        return AccountDto.from(SecurityUtil.getCurrentId()
                .flatMap(accountRepository::findOneWithRolesById)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }
}
