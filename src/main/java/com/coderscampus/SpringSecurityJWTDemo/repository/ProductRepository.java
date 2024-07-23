package com.coderscampus.SpringSecurityJWTDemo.repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
