package com.example.MyCookBook.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorRecipe {

    static final String NO_RECIPES = "There are no recipes in the system.";

    static final String NO_SUCH_RECIPE = "There is no recipe with id %s in the system.";

    static final String RECIPE_ALREADY_EXISTS = "The recipe %s already exists.";


    public static ResponseEntity<CustomErrorType> errorNoRecipes() {
        return new ResponseEntity<>(new CustomErrorType(NO_RECIPES), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomErrorType> errorRecipeAlreadyExists(String name) {
        return new ResponseEntity<>(new CustomErrorType(String.format(RECIPE_ALREADY_EXISTS, name)), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomErrorType> errorNoSuchRecipe(long recipeId) {
        return new ResponseEntity<>(new CustomErrorType(String.format(NO_SUCH_RECIPE, recipeId)), HttpStatus.BAD_REQUEST);
    }
}
