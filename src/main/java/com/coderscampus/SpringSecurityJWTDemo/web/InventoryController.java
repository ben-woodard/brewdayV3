package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import com.coderscampus.SpringSecurityJWTDemo.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long ingredientId){
        return ingredientService.deleteIngredient(ingredientId);
    }
}
