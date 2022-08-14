package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.LicenseDto;
import com.teamdev.licenseservice.dto.LicenseProductDto;
import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.entity.License;
import com.teamdev.licenseservice.entity.LicenseProduct;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicateLicenseProductException;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import com.teamdev.licenseservice.repository.LicenseProductRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));

        Product product = productService.getProductByName(ProductDto.builder().name(licenseProductDto.getProductName()).build());
        License license = licenseService.getLicenseByKey(LicenseDto.builder().licenseKey(licenseProductDto.getLicenseKey()).build());

        List<LicenseProduct> list = licenseProductRepository.findLicenseProductByLicenseKeyAndProductNameQ(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());
        if (!list.isEmpty()) {
            throw new DuplicateLicenseProductException(licenseProductDto.getLicenseKey(), licenseProductDto.getProductName());
        }

        LicenseProduct licenseProduct = LicenseProduct.builder()
                .license(license)
                .product(product)
                .numOfAuthAvailable(licenseProductDto.getNumOfAuthAvailable())
                .isActivated(licenseProductDto.isActivated())
                .expireAt(licenseProductDto.getExpireAt())
                .build();

        return LicenseProductDto.from(licenseProductRepository.save(licenseProduct));
    }
}
