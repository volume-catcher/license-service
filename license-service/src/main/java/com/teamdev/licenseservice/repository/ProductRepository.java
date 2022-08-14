package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);
    List<Product> findAllByAccountId(String id);
    List<Product> findAll();
}
