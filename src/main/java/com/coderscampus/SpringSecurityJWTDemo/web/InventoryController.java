package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import com.coderscampus.SpringSecurityJWTDemo.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final IngredientService ingredientService;
//    private final UserServiceImpl userService;

    @GetMapping("/{companyId}")
    public List<IngredientDto> getAllIngredientsByUser(@PathVariable Long companyId) {
        return ingredientService.findAllByCompany(companyId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{companyId}/create")
    public IngredientDto postCreateIngredient(@RequestBody IngredientDto ingredientDto, @PathVariable Long companyId) {
        return ingredientService.saveIngredient(ingredientDto, companyId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{ingredientId}")
    public IngredientDto updateIngredient(@PathVariable Long ingredientId, @RequestBody IngredientDto ingredientDto) {
        return ingredientService.updateIngredient(ingredientId, ingredientDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long ingredientId){
        return ingredientService.deleteIngredient(ingredientId);
    }
}
