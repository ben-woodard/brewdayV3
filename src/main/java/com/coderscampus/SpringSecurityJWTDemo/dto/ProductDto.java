package com.coderscampus.SpringSecurityJWTDemo.dto;

import com.coderscampus.SpringSecurityJWTDemo.domain.Batch;
import com.coderscampus.SpringSecurityJWTDemo.domain.Recipe;
import com.coderscampus.SpringSecurityJWTDemo.domain.Turn;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String productName;
    private Long defaultRecipeId;
    private User user;
    private List<Recipe> recipes;
    private List<Batch> batches = new ArrayList<>();
    private String selectedRecipe;
    private List<Turn> turns;
}

