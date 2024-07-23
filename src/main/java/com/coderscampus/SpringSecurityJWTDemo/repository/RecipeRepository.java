package com.coderscampus.SpringSecurityJWTDemo.repository;

import com.coderscampus.SpringSecurityJWTDemo.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
