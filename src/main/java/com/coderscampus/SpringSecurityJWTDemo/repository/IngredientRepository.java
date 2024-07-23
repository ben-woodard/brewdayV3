package com.coderscampus.SpringSecurityJWTDemo.repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
