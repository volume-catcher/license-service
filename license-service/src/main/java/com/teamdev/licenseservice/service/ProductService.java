package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.ProductDto;
import com.teamdev.licenseservice.dto.ProductNameDto;
import com.teamdev.licenseservice.entity.Account;
import com.teamdev.licenseservice.entity.Product;
import com.teamdev.licenseservice.exception.DuplicatedException;
import com.teamdev.licenseservice.exception.ErrorMessage;
import com.teamdev.licenseservice.exception.ForbiddenException;
import com.teamdev.licenseservice.exception.NotFoundException;
import com.teamdev.licenseservice.repository.AccountRepository;
import com.teamdev.licenseservice.repository.ProductRepository;
import com.teamdev.licenseservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public ProductNameDto createProduct(ProductNameDto productNameDto) {
        String id = SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

        if (productRepository.findByName(productNameDto.getName()).isPresent()) {
            throw new DuplicatedException(ErrorMessage.PRODUCT_DUPLICATED);
        }

        ProductDto productDto = ProductDto.builder()
                .name(productNameDto.getName())
                .id(id)
                .build();

        saveProductWithValidAccount(productDto);

        return ProductNameDto.fromProductDto(productDto);
    }

    public List<ProductNameDto> getProductsCreatedById(String id) {
        if (!SecurityUtil.getCurrentId().orElseThrow(() -> new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)).equals(id)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return productRepository.findAllByAccountId(id).stream().map(ProductNameDto::from).collect(Collectors.toList());
    }

    public List<ProductNameDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductNameDto::from).collect(Collectors.toList());
    }

    @Transactional
    public Product saveProductWithValidAccount(ProductDto productDto) {
        Optional<Account> account = accountRepository.findById(productDto.getId());

        if (account.isEmpty()) {
            throw new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND);
        }

        Product product = Product.builder()
                .name(productDto.getName())
                .account(account.get())
                .build();

        return productRepository.save(product);
    }

    public Product getProductByName(ProductNameDto productNameDto) {
        Optional<Product> product = productRepository.findByName(productNameDto.getName());

        if (product.isEmpty()) {
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        return product.get();
    }

}
