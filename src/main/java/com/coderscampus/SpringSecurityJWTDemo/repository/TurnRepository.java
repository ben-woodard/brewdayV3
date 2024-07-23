package com.coderscampus.SpringSecurityJWTDemo.repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> {
}
