package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.*;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicatedException;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.repository.LicenseProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseProductService {

    private final LicenseProductRepository licenseProductRepository;
    private final ProductService productService;
    private final LicenseService licenseService;

    @Transactional
    public LicenseProductDto createLicenseProduct(LicenseProductDto licenseProductDto) {
        Product product = productService.getProductByName(ProductNameDto.builder().name(licenseProductDto.getProductName()).build());
        License license = licenseService.getLicenseByKey(LicenseKeyDto.builder().key(licenseProductDto.getLicenseKey()).build());

        LicenseProduct preLicenseProduct = licenseProductRepository
                .findOneLicenseProductByLicenseKeyAndProductNameQ(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());
        if (preLicenseProduct != null) {
            throw new DuplicatedException(ErrorMessage.LICENSEPRODUCT_DUPLICATED);
        }

        LicenseProduct licenseProduct = LicenseProduct.builder()
                .license(license)
                .product(product)
                .numOfAuthAvailable(licenseProductDto.getNumOfAuthAvailable())
                .isActivated(licenseProductDto.getIsActivated())
                .expireAt(licenseProductDto.getExpireAt())
                .build();

        return LicenseProductDto.from(licenseProductRepository.save(licenseProduct));
    }

    @Transactional
    public LicenseProductDto updateLicenseProductIsActivated(LicenseProductIsActivatedDto licenseProductIsActivatedDto) {
        LicenseProduct licenseProduct = licenseProductRepository
                .findOneLicenseProductByLicenseKeyAndProductNameQ(licenseProductIsActivatedDto.getLicenseKey(), licenseProductIsActivatedDto.getProductName());

        if (licenseProduct == null) {
            throw new NotFoundException(ErrorMessage.LICENSEPRODUCT_NOT_FOUND);
        }

        licenseProduct.setIsActivated(licenseProductIsActivatedDto.getIsActivated());

        return LicenseProductDto.from(licenseProductRepository.save(licenseProduct));
    }

    @Transactional
    public LicenseProductDto updateLicenseProduct(LicenseProductDto licenseProductDto) {
        LicenseProduct licenseProduct = licenseProductRepository
                .findOneLicenseProductByLicenseKeyAndProductNameQ(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());

        if (licenseProduct == null) {
            throw new NotFoundException(ErrorMessage.LICENSEPRODUCT_NOT_FOUND);
        }

        licenseProduct.setIsActivated(licenseProductDto.getIsActivated());
        licenseProduct.setNumOfAuthAvailable(licenseProductDto.getNumOfAuthAvailable());
        licenseProduct.setExpireAt(licenseProductDto.getExpireAt());

        return LicenseProductDto.from(licenseProductRepository.save(licenseProduct));
    }

    public List<LicenseProductDto> getAllLicenseProducts() {
        return licenseProductRepository.findAll().stream().map(LicenseProductDto::from).collect(Collectors.toList());
    }

    public List<LicenseProductDto> getLicenseProductsByLicenseKey(String licenseKey) {
        return licenseProductRepository
                .findLicenseProductWithProductByLicenseKey(licenseKey)
                .stream()
                .map(LicenseProductDto::from)
                .collect(Collectors.toList());
    }

    public List<LicenseProductNumDto> getLicenseProductsNum() {
        return licenseProductRepository.countAllLicenseProduct();
    }
}
