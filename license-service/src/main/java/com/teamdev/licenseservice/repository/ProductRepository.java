package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Page<ProductEntity> findAll(Pageable pageable);
    Page<ProductEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Optional<ProductEntity> findByName(String name);
}
