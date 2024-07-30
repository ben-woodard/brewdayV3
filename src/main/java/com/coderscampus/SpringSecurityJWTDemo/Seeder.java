package com.coderscampus.SpringSecurityJWTDemo;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.Role;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignUpRequest;
import com.coderscampus.SpringSecurityJWTDemo.repository.IngredientRepository;
import com.coderscampus.SpringSecurityJWTDemo.repository.UserRepository;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationService;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationServiceImpl;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

//@Component
//@AllArgsConstructor
//public class Seeder implements CommandLineRunner {
//    private final IngredientRepository ingredientRepo;
//    private final UserRepository userRepo;
//    private final UserService userService;
//    private final AuthenticationServiceImpl authenticationService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        SignUpRequest signUp = new SignUpRequest(
//                "joe@joe.com",
//                "vvvvv",
//                "Joe",
//                "Schmo",
//                "Joes Brewing",
//                Optional.of("")
//        );
//
//        authenticationService.signup(signUp);
//        User user1 = userRepo.findById(1).orElse(null);
//
//        Ingredient ingredient1 = new Ingredient();
//        ingredient1.setIngredientName("2-Row Barley");
//        ingredient1.setAmountInStock(100.0);
//        ingredient1.setOrderingThreshold(50.0);
//        ingredient1.setIngredientType(Ingredient.ingredientType.MALT);
//        ingredient1.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);
//        ingredient1.setUser(user1);
//
//        Ingredient ingredient2 = new Ingredient();
//        ingredient2.setIngredientName("Citra");
//        ingredient2.setAmountInStock(50.0);
//        ingredient2.setOrderingThreshold(10.0);
//        ingredient2.setIngredientType(Ingredient.ingredientType.HOP);
//        ingredient2.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);
//        ingredient2.setUser(user1);
//
//        Ingredient ingredient3 = new Ingredient();
//        ingredient3.setIngredientName("Citra");
//        ingredient3.setAmountInStock(50.0);
//        ingredient3.setOrderingThreshold(10.0);
//        ingredient3.setIngredientType(Ingredient.ingredientType.HOP);
//        ingredient3.setUnitOfMeasurement(Ingredient.unitOfMeasurement.lBS);
//        ingredient3.setUser(user1);
//
//        assert user1 != null;
//        user1.getIngredients().addAll(Arrays.asList(
//                new Ingredient[]{ingredient1, ingredient2, ingredient3}));
//
//        ingredientRepo.saveAllAndFlush(Arrays.asList(
//                new Ingredient[]{ingredient1, ingredient2, ingredient3}));
//    }
//}
