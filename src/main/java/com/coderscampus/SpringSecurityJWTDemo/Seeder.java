package com.coderscampus.SpringSecurityJWTDemo;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
    private final IngredientRepository ingredientRepo;

    @Override
    public void run(String... args) throws Exception {

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredientName("2-Row Barley");
        ingredient1.setAmountInStock(100.0);
        ingredient1.setOrderingThreshold(50.0);
        ingredient1.setIngredientType(Ingredient.ingredientType.MALT);
        ingredient1.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredientName("Citra");
        ingredient2.setAmountInStock(50.0);
        ingredient2.setOrderingThreshold(10.0);
        ingredient2.setIngredientType(Ingredient.ingredientType.HOP);
        ingredient2.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setIngredientName("Citra");
        ingredient3.setAmountInStock(50.0);
        ingredient3.setOrderingThreshold(10.0);
        ingredient3.setIngredientType(Ingredient.ingredientType.HOP);
        ingredient3.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);

        ingredientRepo.saveAllAndFlush(Arrays.asList(
                new Ingredient[]{ingredient1, ingredient2, ingredient3}));

    }
}
