package com.kroogle.tacocloud.controller;

import com.kroogle.tacocloud.data.jpa.OrderRepository;
import com.kroogle.tacocloud.model.Order;
import com.kroogle.tacocloud.model.user.User;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        LOGGER.info("Order submitted: " + order);

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/taco";
    }
}
