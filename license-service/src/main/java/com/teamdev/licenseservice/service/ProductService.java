package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ProductResponseDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.*;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.ProductRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, AccountRepository accountRepository) {
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public ProductResponseDto createProduct(ProductDto productDto) {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

        if (productRepository.findByName(productDto.getName()).isPresent()) {
            throw new DuplicatedException(ErrorMessage.PRODUCT_DUPLICATED);
        }

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(productDto.getName())
                .id(id)
                .build();

        saveProductWithValidAccount(productResponseDto);

        return productResponseDto;
    }

    public List<ProductResponseDto> getProductsCreatedByMe() {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
        return productRepository.findAllByAccountId(id).stream().map(ProductResponseDto::from).collect(Collectors.toList());
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public Product saveProductWithValidAccount(ProductResponseDto productResponseDto) {
        Optional<Account> account = accountRepository.findById(productResponseDto.getId());

        if (account.isEmpty()) {
            throw new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND);
        }

        Product product = Product.builder()
                .name(productResponseDto.getName())
                .account(account.get())
                .build();

        return productRepository.save(product);
    }

    public Product getProductByName(ProductDto productDto) {
        Optional<Product> product = productRepository.findByName(productDto.getName());

        if (product.isEmpty()) {
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        return product.get();
    }

}
