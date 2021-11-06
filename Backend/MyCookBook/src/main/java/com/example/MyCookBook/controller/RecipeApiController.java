package com.example.MyCookBook.controller;

import com.example.MyCookBook.DTO.RecipeDTO;
import com.example.MyCookBook.model.Category;
import com.example.MyCookBook.model.Recipe;
import com.example.MyCookBook.service.RecipeService;
import com.example.MyCookBook.util.ErrorRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RecipeApiController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public ResponseEntity<?> listRecipes(){

        List<Recipe> recipes = recipeService.listRecipes();
        if(recipes.isEmpty())
            return ErrorRecipe.errorNoRecipes();

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe", method = RequestMethod.POST)
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDTO recipeDTO){

        if(recipeService.getRecipeByName(recipeDTO.getName()).isPresent())
            return ErrorRecipe.errorRecipeAlreadyExists(recipeDTO.getName());

        Recipe recipe = recipeService.createRecipe(recipeDTO);

        return new ResponseEntity<Recipe>(recipe, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/recipe/{recipeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("recipeId") long recipeId){

        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        if(recipeOptional.isEmpty())
            return ErrorRecipe.errorNoSuchRecipe(recipeId);

        Recipe recipe = recipeOptional.get();
        recipeService.deleteRecipe(recipe);

        return new ResponseEntity<String>(recipe.getName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe/{recipeId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRecipe(@PathVariable("recipeId") long recipeId, @RequestBody RecipeDTO recipeDTO){

        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        if(recipeOptional.isEmpty())
            return ErrorRecipe.errorNoSuchRecipe(recipeId);

        Recipe recipe = recipeService.updateRecipe(recipeOptional.get(), recipeDTO);

        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipes/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<?> getRecipesbyCategory(@PathVariable("category") Category category){

        List<Recipe> recipes = recipeService.getRecipesByCategory(category);
        if(recipes.isEmpty())
            return ErrorRecipe.errorNoRecipesCategory();

        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipes/ingredients", method = RequestMethod.POST)
    public ResponseEntity<?> getRecipesByIngredients(@RequestBody List<String> ingredients){

        List<Recipe> recipes = recipeService.getRecipesByIngredients(ingredients);
        if(recipes.isEmpty())
            return ErrorRecipe.errorNoRecipesIngredients();

        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipes/ingredients", method = RequestMethod.GET)
    public ResponseEntity<?> getAllIngredients(){

        List<Recipe> recipes = recipeService.listRecipes();
        if(recipes.isEmpty())
            return ErrorRecipe.errorNoRecipes();
        List<String> ingredients = recipeService.listAllIngredients();
        return new ResponseEntity<List<String>>(ingredients, HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe/random", method = RequestMethod.GET)
    public ResponseEntity<?> getRandomRecipe(){

        List<Recipe> recipes = recipeService.listRecipes();
        if(recipes.isEmpty())
            return ErrorRecipe.errorNoRecipes();

        Recipe recipe = recipeService.getRandomRecipe();
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


}
