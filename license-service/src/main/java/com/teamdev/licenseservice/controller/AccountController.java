package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.AccountDto;
import com.teamdev.licenseservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.signUp(accountDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AccountDto> getMyUserInfo() {
        return ResponseEntity.ok(accountService.getMyAccountWithRoles());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AccountDto> getUserInfo(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountWithRoles(id));
    }
}
