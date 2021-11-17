package com.example.MyCookBook.service;

import com.example.MyCookBook.DTO.RecipeDTO;
import com.example.MyCookBook.model.Category;
import com.example.MyCookBook.model.Ingredient;
import com.example.MyCookBook.model.Recipe;
import com.example.MyCookBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        Recipe recipe = new Recipe(recipeDTO.getName(), recipeDTO.getAssetsName(), recipeDTO.getIngredients(),
                recipeDTO.getInstructions(), recipeDTO.getCategories(), recipeDTO.getDescription(), recipeDTO.getParagraph(),
                recipeDTO.getAsideTitle(), recipeDTO.getAsideFirstText(), recipeDTO.getAsideList(), recipeDTO.getAsideSecondText());
        saveRecipe(recipe);
        return recipe;
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    public Recipe updateRecipe(Recipe recipe, RecipeDTO recipeDTO) {
        recipe.setCategories(recipeDTO.getCategories());
        recipe.setAssetsName(recipeDTO.getAssetsName());
        recipe.setIngredients(recipeDTO.getIngredients());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.setName(recipeDTO.getName());
        return recipeRepository.save(recipe);
    }

    public List<Recipe> listRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRandomRecipe() {
        List<Recipe> recipes = listRecipes();
        int randomIndex = (int) (Math.random() * recipes.size());

        return recipes.get(randomIndex);
    }

    public List<Recipe> getRecipesByCategory(Category category) {
        List<Recipe> recipes = listRecipes();
        List<Recipe> result = new ArrayList<>();
        for(Recipe r : recipes){
            if(r.hasCategory(category))
                result.add(r);
        }
        return result;
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> recipes = listRecipes();
        List<Recipe> result = new ArrayList<>();
        boolean pass;
        for(Recipe r : recipes){
            pass=true;
            List<Ingredient> recipeIngredients = r.getIngredients();
            for(Ingredient ingredient : recipeIngredients){
                if (!ingredients.contains(ingredient.getName()))
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
            List<Ingredient> recipeIngredients = r.getIngredients();
            for(Ingredient i : recipeIngredients){
                if(!ingredients.contains(i.getName()))
                    ingredients.add(i.getName());
            }
        }
        Collections.sort(ingredients);
        return ingredients;
    }
}
