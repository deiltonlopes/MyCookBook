package com.example.MyCookBook.service;

import com.example.MyCookBook.DTO.RecipeDTO;
import com.example.MyCookBook.model.Category;
import com.example.MyCookBook.model.Recipe;
import com.example.MyCookBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Optional<Recipe> getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    public Recipe createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe(recipeDTO.getName(), recipeDTO.getAssetsName(), recipeDTO.getIngredients(), recipeDTO.getInstructions(), recipeDTO.getCategories());
        saveRecipe(recipe);
        return recipe;
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    public List<Recipe> listRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipesByCategories(List<Category> categories) {
        List<Recipe> recipes = listRecipes();
        List<Recipe> result = new ArrayList<Recipe>();
        boolean pass;
        for(Recipe r : recipes){
            pass = false;
            for(Category c : categories){
                if(r.hasCategory(c))
                    pass=true;
            }
            if(pass)
                result.add(r);
        }
        return result;
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> recipes = listRecipes();
        List<Recipe> result = new ArrayList<Recipe>();
        boolean pass;
        for(Recipe r : recipes){
            pass=true;
            List<String> recipeIngredients = r.getIngredients();
            for(String ingredient : recipeIngredients){
                if (!ingredients.contains(ingredient))
                    pass = false;
            }
            if(pass)
                result.add(r);
        }
        return result;
    }

    public List<String> listAllIngredients() {
        List<String> ingredients = new ArrayList<>();
        List<Recipe> recipes = listRecipes();
        for(Recipe r : recipes){
            List<String> recipeIngredients = r.getIngredients();
            for(String i : recipeIngredients){
                if(!ingredients.contains(i))
                    ingredients.add(i);
            }
        }
        return ingredients;
    }
}
