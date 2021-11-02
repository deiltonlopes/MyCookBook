package com.example.MyCookBook.service;

import com.example.MyCookBook.DTO.RecipeDTO;
import com.example.MyCookBook.model.Category;
import com.example.MyCookBook.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Optional<Recipe> getRecipeById(Long id);

    Optional<Recipe> getRecipeByName(String name);

    Recipe createRecipe(RecipeDTO recipeDTO);

    void saveRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe, RecipeDTO recipeDTO);

    List<Recipe> listRecipes();

    List<Recipe> getRecipesByCategory(Category category);

    List<Recipe> getRecipesByIngredients(List<String> ingredients);

    List<String> listAllIngredients();
}
