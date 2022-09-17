package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.*;
import com.teamdev.licenseservice.entity.LicenseEntity;
import com.teamdev.licenseservice.entity.ContractEntity;
import com.teamdev.licenseservice.entity.ProductEntity;
import com.teamdev.licenseservice.exception.DuplicatedException;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractService {

    private final ContractRepository contractRepository;
    private final ProductService productService;
    private final LicenseService licenseService;

    @Transactional
    public ContractDto createContract(ContractDto contractDto) {
        ProductEntity productEntity = productService.getProductByName(ProductNameDto.builder().name(contractDto.getProductName()).build());
        LicenseEntity licenseEntity = licenseService.getLicenseByKey(LicenseKeyDto.builder().key(contractDto.getLicenseKey()).build());

        ContractEntity preContractEntity = contractRepository
                .findOneContractByLicenseKeyAndProductNameQ(contractDto.getLicenseKey(), contractDto.getProductName());
        if (preContractEntity != null) {
            throw new DuplicatedException(ErrorMessage.CONTRACT_DUPLICATED);
        }

        ContractEntity contractEntity = ContractEntity.builder()
                .license(licenseEntity)
                .product(productEntity)
                .numOfAuthAvailable(contractDto.getNumOfAuthAvailable())
                .isActivated(contractDto.getIsActivated())
                .expireAt(contractDto.getExpireAt())
                .build();

        return ContractDto.from(contractRepository.save(contractEntity));
    }

    @Transactional
    public ContractDto updateContractIsActivated(ContractIsActivatedDto contractIsActivatedDto) {
        ContractEntity contractEntity = contractRepository
                .findOneContractByLicenseKeyAndProductNameQ(contractIsActivatedDto.getLicenseKey(), contractIsActivatedDto.getProductName());

        if (contractEntity == null) {
            throw new NotFoundException(ErrorMessage.CONTRACT_NOT_FOUND);
        }

        contractEntity.setIsActivated(contractIsActivatedDto.getIsActivated());

        return ContractDto.from(contractRepository.save(contractEntity));
    }

    @Transactional
    public ContractDto updateContract(ContractDto contractDto) {
        ContractEntity contractEntity = contractRepository
                .findOneContractByLicenseKeyAndProductNameQ(contractDto.getLicenseKey(), contractDto.getProductName());

        if (contractEntity == null) {
            throw new NotFoundException(ErrorMessage.CONTRACT_NOT_FOUND);
        }

        contractEntity.setIsActivated(contractDto.getIsActivated());
        contractEntity.setNumOfAuthAvailable(contractDto.getNumOfAuthAvailable());
        contractEntity.setExpireAt(contractDto.getExpireAt());

        return ContractDto.from(contractRepository.save(contractEntity));
    }

    public List<ContractDto> getAllContract() {
        return contractRepository.findAll().stream().map(ContractDto::from).collect(Collectors.toList());
    }

    public List<ContractDto> getContractByLicenseKey(String licenseKey) {
        return contractRepository
                .findContractWithProductByLicenseKey(licenseKey)
                .stream()
                .map(ContractDto::from)
                .collect(Collectors.toList());
    }

}
