package com.example.MyCookBook.DTO;

import com.example.MyCookBook.model.Category;

import java.util.List;

public class RecipeDTO {

    private List<String> ingredients;

    private List<String> instructions;

    private List<Category> categories;

    private String name;

    private String assetsName;

    private String description;

    private String paragraph;

    private String asideTitle;

    private String asideFirstText;

    private String[] asideList;

    private String asideSecondText;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getAsideTitle() {
        return asideTitle;
    }

    public void setAsideTitle(String asideTitle) {
        this.asideTitle = asideTitle;
    }

    public String getAsideFirstText() {
        return asideFirstText;
    }

    public void setAsideFirstText(String asideFirstText) {
        this.asideFirstText = asideFirstText;
    }

    public String[] getAsideList() {
        return asideList;
    }

    public void setAsideList(String[] asideList) {
        this.asideList = asideList;
    }

    public String getAsideSecondText() {
        return asideSecondText;
    }

    public void setAsideSecondText(String asideSecondText) {
        this.asideSecondText = asideSecondText;
    }
}
