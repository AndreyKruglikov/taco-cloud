package com.kroogle.tacocloud.model;

import java.sql.Date;
import java.util.List;

public class Taco {

    private Long id;
    private Date createdAt;

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

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
