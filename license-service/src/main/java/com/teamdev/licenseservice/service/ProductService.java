package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ProductResponseDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicateProductException;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import com.teamdev.licenseservice.exception.NotFoundProductException;
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

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, AccountRepository accountRepository) {
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public ProductResponseDto createProduct(ProductDto productDto) {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));

        if (productRepository.findByName(productDto.getName()).isPresent()) {
            throw new DuplicateProductException(productDto.getName());
        }

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(productDto.getName())
                .id(id).
                build();

        saveProductWithValidAccount(productResponseDto);

        logger.debug("제품을 생성하였습니다, id: {}, 제품: {}", id, productResponseDto.getName());

        return productResponseDto;
    }

    public List<ProductResponseDto> getProductsCreatedByMe() {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));
        return productRepository.findAllByAccountId(id).stream().map(ProductResponseDto::from).collect(Collectors.toList());
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public Product saveProductWithValidAccount(ProductResponseDto productResponseDto) {
        Optional<Account> account = accountRepository.findById(productResponseDto.getId());

        if (account.isEmpty()) {
            throw new NotFoundAccountException(productResponseDto.getId());
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
            throw new NotFoundProductException(productDto.getName());
        }

        return product.get();
    }

}
