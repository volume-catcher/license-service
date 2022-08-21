package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.dto.LicenseProductDto;
import com.teamdev.licenseservice.dto.LicenseProductIsActivatedDto;
import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicateLicenseProductException;
import com.teamdev.licenseservice.exception.NotFoundLicenseProductException;
import com.teamdev.licenseservice.repository.LicenseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseProductService {

    private final LicenseProductRepository licenseProductRepository;
    private final ProductService productService;
    private final LicenseService licenseService;

    @Autowired
    public LicenseProductService(LicenseProductRepository licenseProductRepository, ProductService productService, LicenseService licenseService) {
        this.licenseProductRepository = licenseProductRepository;
        this.productService = productService;
        this.licenseService = licenseService;
    }

    @Transactional
    public LicenseProductDto createLicenseProduct(LicenseProductDto licenseProductDto) {
        Product product = productService.getProductByName(ProductDto.builder().name(licenseProductDto.getProductName()).build());
        License license = licenseService.getLicenseByKey(LicenseDto.builder().licenseKey(licenseProductDto.getLicenseKey()).build());

        LicenseProduct preLicenseProduct = licenseProductRepository.findOneLicenseProductByLicenseKeyAndProductNameQ(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());
        if (preLicenseProduct != null) {
            throw new DuplicateLicenseProductException(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());
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
        Product product = productService.getProductByName(ProductDto.builder().name(licenseProductIsActivatedDto.getProductName()).build());
        License license = licenseService.getLicenseByKey(LicenseDto.builder().licenseKey(licenseProductIsActivatedDto.getLicenseKey()).build());

        LicenseProduct licenseProduct = licenseProductRepository.findOneLicenseProductByLicenseKeyAndProductNameQ(licenseProductIsActivatedDto.getLicenseKey(), licenseProductIsActivatedDto.getProductName());
        if (licenseProduct == null) {
            throw new NotFoundLicenseProductException(licenseProductIsActivatedDto.getLicenseKey(), licenseProductIsActivatedDto.getProductName());
        }

        licenseProduct.setIsActivated(licenseProductIsActivatedDto.getIsActivated());

        return LicenseProductDto.from(licenseProductRepository.save(licenseProduct));
    }

    public List<LicenseProductDto> getAllLicenseProducts() {
        return licenseProductRepository.findAll().stream().map(LicenseProductDto::from).collect(Collectors.toList());
    }
}
