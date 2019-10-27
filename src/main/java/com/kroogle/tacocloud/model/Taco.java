package com.kroogle.tacocloud.model;

import java.util.List;

public class Taco {

    private String name;
    private List<String> ingredients;

    public Taco() {
    }

    public Taco(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
