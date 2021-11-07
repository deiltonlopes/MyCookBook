package com.example.MyCookBook.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Ingredient> ingredients;

    @Column(name="instructions")
    @ElementCollection(targetClass=String.class)
    private List<String> instructions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    private String name;

    private String assetsName;

    @Lob
    private String description;

    @Lob
    private String paragraph;

    private String asideTitle;

    @Lob
    private String asideFirstText;

    private String[] asideList;

    @Lob
    private String asideSecondText;

    private Recipe(){}

    public Recipe(String name, String assetsName, List<Ingredient> ingredients, List<String> instructions,
                  List<Category> categories, String description, String paragraph, String asideTitle,
                  String asideFirstText, String[] asideList, String asideSecondText) {
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.categories = categories;
        this.name = name;
        this.assetsName = assetsName;
        this.description = description;
        this.paragraph = paragraph;
        this.asideTitle = asideTitle;
        this.asideFirstText = asideFirstText;
        this.asideList = asideList;
        this.asideSecondText = asideSecondText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
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
