package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.AccountDto;
import com.teamdev.licenseservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signUp(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.signUp(accountDto));
    }

    @GetMapping("/user")
    public ResponseEntity<AccountDto> getMyUserInfo() {
        return ResponseEntity.ok(accountService.getMyAccountWithRoles());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AccountDto> getUserInfo(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountWithRoles(id));
    }
}
