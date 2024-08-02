package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.Order;
import com.coderscampus.SpringSecurityJWTDemo.domain.Recipe;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IngredientDto {

    private Long ingredientId;

    private String ingredientName;

    private List<Recipe> recipes;

    private List<Order> orders;

    private Double amountInStock;

    private Double orderingThreshold;

    private Ingredient.ingredientType ingredientType;

    private Ingredient.unitOfMeasurement unitOfMeasurement;

    private User user;


}
