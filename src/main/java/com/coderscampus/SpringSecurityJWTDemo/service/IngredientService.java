package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IngredientService {

    IngredientDto saveIngredient(IngredientDto ingredientDto, Long companyId);

    IngredientDto updateIngredient(Long ingredientId, IngredientDto ingredientDto);

    ResponseEntity<String> deleteIngredient(Long ingredientId);

    List<IngredientDto> findAllByCompany(Long companyId);
}
