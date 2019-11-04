package com.kroogle.tacocloud.utils.converter;

import com.kroogle.tacocloud.data.IngredientRepository;
import com.kroogle.tacocloud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String ingredientId) {
        return ingredientRepository.findOne(ingredientId);
    }
}
