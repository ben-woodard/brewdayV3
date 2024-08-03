package com.coderscampus.SpringSecurityJWTDemo.service.impl;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import com.coderscampus.SpringSecurityJWTDemo.mappers.IngredientMapper;
import com.coderscampus.SpringSecurityJWTDemo.repository.IngredientRepository;
import com.coderscampus.SpringSecurityJWTDemo.service.IngredientService;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientMapper ingredientMapper;
    private final UserService userService;
    private final IngredientRepository ingredientRepo;

    @Override
    public IngredientDto saveIngredient(IngredientDto ingredientDto, Integer userId) {
        Ingredient ingredient = ingredientMapper.dtoToEntity(ingredientDto);
        User user = userService.findById(userId);
        if(user == null) {
            throw new RuntimeException("User Not Found");
        }
        ingredient.setUser(user);
        user.getIngredients().add(ingredient);
        return ingredientMapper.entityToDto(ingredientRepo.save(ingredient));

    }

    @Override
    public List<IngredientDto> findAllByUser(Integer userId) {
        User user = userService.findById(userId);
        if(user == null) {
            throw new RuntimeException("User Not Found");
        }
        List<Ingredient> ingredients = ingredientRepo.findAllByUser(user);
        ingredients.sort(Comparator.comparing(Ingredient::getIngredientName));
        return ingredientMapper.entityListToDtoList(ingredients);
    }

    @Override
    public IngredientDto updateIngredient(Long ingredientId, IngredientDto ingredientDto) {
        Ingredient dbIngredient = ingredientRepo.findById(ingredientId).orElse(null);
        if(dbIngredient == null) {
            throw new RuntimeException("Wrong");
        }
        Ingredient ingredient = ingredientMapper.dtoToEntity(ingredientDto);
        ingredient.setUser(dbIngredient.getUser());
        return ingredientMapper.entityToDto(ingredientRepo.saveAndFlush(ingredient));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteIngredient(Long ingredientId) {
        Ingredient ingredient = checkIngredientNull(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found");
        }
        ingredient.setUser(null);
        ingredient.getOrders().clear();
        ingredient.getRecipes().clear();
        ingredientRepo.delete(ingredient);


        if (checkIngredientNull(ingredientId) == null) {
            return ResponseEntity.ok("Item Successfully Deleted");
        } else {
            throw new RuntimeException("There was an error deleting this inventory item");
        }
    }

    public Ingredient checkIngredientNull(Long ingredientId) {
        Ingredient ingredient = ingredientRepo.findById(ingredientId).orElse(null);
        if(ingredient != null) {
           return ingredient;
        }
        return null;
    }

}
