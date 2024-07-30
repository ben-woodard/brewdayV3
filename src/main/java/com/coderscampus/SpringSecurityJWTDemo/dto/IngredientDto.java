package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {

    private Long ingredientId;

    private String ingredientName;

    private Double amountInStock;

    private Double orderingThreshold;

    private Ingredient.ingredientType ingredientType;

    private Ingredient.unitOfMeasurement unitOfMeasurement;
}
