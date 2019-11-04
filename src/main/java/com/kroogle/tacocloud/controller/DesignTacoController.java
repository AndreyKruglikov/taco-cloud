package com.kroogle.tacocloud.controller;

import com.kroogle.tacocloud.data.IngredientRepository;
import com.kroogle.tacocloud.model.Ingredient;
import com.kroogle.tacocloud.model.Taco;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kroogle.tacocloud.model.Ingredient.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        LOGGER.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        List<Ingredient> result = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (type == ingredient.getType()) {
                result.add(ingredient);
            }
        }
        return result;
    }
}
