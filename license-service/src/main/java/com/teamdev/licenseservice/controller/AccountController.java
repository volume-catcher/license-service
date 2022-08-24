package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.AccountDto;
import com.teamdev.licenseservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AccountDto signUp(@Valid @RequestBody AccountDto accountDto) {
        return accountService.signUp(accountDto);
    }

    @GetMapping("/user")
    public AccountDto getMyUserInfo() {
        return accountService.getMyAccountWithRoles();
    }

    @GetMapping("/user/{id}")
    public AccountDto getUserInfo(@PathVariable String id) {
        return accountService.getAccountWithRoles(id);
    }
}
