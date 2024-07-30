package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;

public interface IngredientService {

    IngredientDto saveIngredient(IngredientDto ingredientDto, Integer userId);
}
