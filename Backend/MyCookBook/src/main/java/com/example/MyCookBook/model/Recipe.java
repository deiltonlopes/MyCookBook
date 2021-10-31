package com.example.MyCookBook.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ingredients")
    @ElementCollection(targetClass=String.class)
    private List<String> ingredients;

    @Column(name="instructions")
    @ElementCollection(targetClass=String.class)
    private List<String> instructions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    private String name;

    private String assetsName;

    private Recipe(){}

    public Recipe(String name, String assetsName, List<String> ingredients, List<String> instructions, List<Category> categories){
        this.name = name;
        this.assetsName = assetsName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean hasCategory(Category category) {
        boolean result = false;
        for(Category c : categories){
            if (c.equals(category)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
