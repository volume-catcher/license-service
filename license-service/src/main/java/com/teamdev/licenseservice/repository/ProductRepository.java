package com.teamdev.licenseservice.repository;

import com.teamdev.licenseservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
