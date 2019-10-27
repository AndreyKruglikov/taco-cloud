package com.kroogle.tacocloud.controller;

import com.kroogle.tacocloud.model.Order;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DesignTacoController.class);

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order) {
        LOGGER.info("Order submitted: " + order);
        return "redirect:/";
    }
}
