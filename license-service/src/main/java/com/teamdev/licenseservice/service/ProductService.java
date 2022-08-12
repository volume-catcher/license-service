package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ReqProductDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicateProductException;
import com.teamdev.licenseservice.exception.NotFoundAccountException;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.ProductRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public ProductDto createProduct(ReqProductDto reqProductDto) {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundAccountException(null));

        if (productRepository.findByName(reqProductDto.getName()).isPresent()) {
            throw new DuplicateProductException(reqProductDto.getName());
        }

        ProductDto productDto = ProductDto.builder()
                .name(reqProductDto.getName())
                .id(id).
                build();

        saveProductWithValidAccount(productDto);

        logger.debug("제품을 생성하였습니다, id: {}, 제품: {}", id, productDto.getName());

        return productDto;
    }

    public Product saveProductWithValidAccount(ProductDto productDto) {
        Optional<Account> account = accountRepository.findById(productDto.getId());

        if (account.isEmpty()) {
            throw new NotFoundAccountException(productDto.getId());
        }

        Product product = Product.builder()
                .name(productDto.getName())
                .account(account.get())
                .build();

        return productRepository.save(product);
    }

}
