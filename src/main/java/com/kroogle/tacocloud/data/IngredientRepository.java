package com.kroogle.tacocloud.data;

import com.kroogle.tacocloud.model.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
