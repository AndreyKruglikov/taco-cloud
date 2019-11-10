package com.kroogle.tacocloud.controller;

import com.kroogle.tacocloud.data.jpa.IngredientRepository;
import com.kroogle.tacocloud.data.jpa.TacoRepository;
import com.kroogle.tacocloud.model.Ingredient;
import com.kroogle.tacocloud.model.Order;
import com.kroogle.tacocloud.model.Taco;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kroogle.tacocloud.model.Ingredient.Type;

@Controller
@RequestMapping("/taco")
@SessionAttributes("order")
public class DesignTacoController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepository;
    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository,
                                TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        return "taco";
    }

    @PostMapping
    public String processDesign(Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "taco";
        }
        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);
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
