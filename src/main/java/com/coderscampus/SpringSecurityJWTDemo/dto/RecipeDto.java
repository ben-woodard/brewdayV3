package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private String recipeName;
    private Product product;
    private List<Ingredient> ingredients;
    private Map<Long, Double> ingredientsToRemove;
}
