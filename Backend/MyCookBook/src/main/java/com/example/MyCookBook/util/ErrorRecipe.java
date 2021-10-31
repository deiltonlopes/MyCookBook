package com.example.MyCookBook.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorRecipe {

    static final String NO_RECIPES = "There are no recipes in the system.";

    static final String NO_SUCH_RECIPE = "There is no such recipe in the system.";


    public static ResponseEntity<CustomErrorType> errorNoRecipes() {
        return new ResponseEntity<>(new CustomErrorType(NO_RECIPES), HttpStatus.NO_CONTENT);
    }
}
