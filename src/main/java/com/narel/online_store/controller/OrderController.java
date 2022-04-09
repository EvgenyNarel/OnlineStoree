package com.narel.online_store.controller;

import com.narel.online_store.dao.User;
import com.narel.online_store.repository.CartItemRepository;
import com.narel.online_store.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private CartItemRepository cartItem;

    @Autowired
    private ShoppingCartServices cartServices;

    @GetMapping
    public String orderList(Model model) {
        model.addAttribute("order", cartItem.findAll());
        return "order-list";
    }

    @GetMapping("/formalized")
    public String orderFormalized() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartServices.updateFormalized((User) principal);
        System.out.println("Оформлен");

        return "redirect:/product";

    }

}
