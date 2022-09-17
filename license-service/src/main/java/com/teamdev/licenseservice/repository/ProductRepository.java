package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByName(String name);
}
