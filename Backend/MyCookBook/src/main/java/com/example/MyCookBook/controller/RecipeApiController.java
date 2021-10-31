package com.example.MyCookBook.controller;

import com.example.MyCookBook.model.Recipe;
import com.example.MyCookBook.service.RecipeService;
import com.example.MyCookBook.util.ErrorRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
