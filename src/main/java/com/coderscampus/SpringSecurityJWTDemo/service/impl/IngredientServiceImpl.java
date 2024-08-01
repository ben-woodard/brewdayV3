package com.coderscampus.SpringSecurityJWTDemo.service.impl;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import com.coderscampus.SpringSecurityJWTDemo.mappers.IngredientMapper;
import com.coderscampus.SpringSecurityJWTDemo.repository.IngredientRepository;
import com.coderscampus.SpringSecurityJWTDemo.service.IngredientService;
import com.coderscampus.SpringSecurityJWTDemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
        Ingredient ingredient = ingredientMapper.dtoToEntity(ingredientDto);
        return ingredientMapper.entityToDto(ingredientRepo.saveAndFlush(ingredient));
    }

}
