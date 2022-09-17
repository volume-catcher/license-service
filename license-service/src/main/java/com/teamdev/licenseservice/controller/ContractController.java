package com.teamdev.licenseservice.controller;

import com.teamdev.licenseservice.dto.ContractDto;
import com.teamdev.licenseservice.dto.ContractIsActivatedDto;
import com.teamdev.licenseservice.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContractDto createContract(@Valid @RequestBody ContractDto contractDto) {
        return contractService.createContract(contractDto);
    }

    @PatchMapping
    public ContractDto updateContractIsActivated(@Valid @RequestBody ContractIsActivatedDto contractIsActivatedDto) {
        return contractService.updateContractIsActivated(contractIsActivatedDto);
    }

    @PutMapping
    public ContractDto updateContract(@Valid @RequestBody ContractDto contractDto) {
        return contractService.updateContract(contractDto);
    }

    @GetMapping
    public List<ContractDto> getAllContract() {
        return contractService.getAllContract();
    }

    @GetMapping("/licenses/{licenseKey}")
    public List<ContractDto> getContractByLicenseKey(@PathVariable String licenseKey) {
        return contractService.getContractByLicenseKey(licenseKey);
    }

}
