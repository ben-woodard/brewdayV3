package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import com.coderscampus.SpringSecurityJWTDemo.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final IngredientService ingredientService;
//    private final UserServiceImpl userService;

    @GetMapping("{userId}")
    public List<IngredientDto> getAllIngredientsByUser(@PathVariable Integer userId) {
        return ingredientService.findAllByUser(userId);
    }

    @PostMapping("/{userId}/create")
    public IngredientDto postCreateIngredient(@RequestBody IngredientDto ingredientDto, @PathVariable Integer userId) {
        return ingredientService.saveIngredient(ingredientDto, userId);
    }

    @PatchMapping("/{ingredientId}")
    public IngredientDto updateIngredient(@PathVariable Long ingredientId, @RequestBody IngredientDto ingredientDto) {
        return ingredientService.updateIngredient(ingredientId, ingredientDto);
    }
//
//    @GetMapping("/{userId}/{ingredientId}")
//    public String getIngredientInfoById(@PathVariable Long ingredientId, ModelMap model, @PathVariable Integer userId) {
//        Ingredient ingredient = ingredientService.findById(ingredientId);
//        model.addAttribute("user", userService.findUserById(userId).orElse(null));
//        model.addAttribute("ingredient", ingredient);
//        model.addAttribute("recipeList", ingredient.getRecipes());
//        return "ingredient/update";
//    }
//
//    @PostMapping("/{userId}/{ingredientId}")
//    public String postUpdateIngredientInfo(@PathVariable Integer userId, @ModelAttribute Ingredient ingredient) {
//        ingredientService.saveIngredientUserRelationship(ingredient, userId);
//        return "redirect:/inventory/{userId}/home";
//    }
//
//    @PostMapping("/{userId}/{ingredientId}/delete")
//    public String postDeleteIngredient(@PathVariable Long ingredientId, @PathVariable Integer userId) {
//        Ingredient ingredient = ingredientService.findById(ingredientId);
//        User user = userService.findUserById(userId).orElse(null);
//        ingredientService.delete(ingredient, user);
//        return "redirect:/inventory/{userId}/home";
//    }
}
