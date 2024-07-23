package com.coderscampus.SpringSecurityJWTDemo.repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
