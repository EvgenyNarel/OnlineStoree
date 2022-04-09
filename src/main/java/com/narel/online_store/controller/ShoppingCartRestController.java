package com.narel.online_store.controller;

import com.narel.online_store.dao.User;
import com.narel.online_store.service.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartServices cartServices;


    @GetMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") Integer quantity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer addedQuantity = cartServices.add(productId, quantity, (User) principal);
        if (principal == null) {
            System.out.println("Нет пользователя");
        }
        return addedQuantity + " шт. добавленно в корзину";
    }

    @GetMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Long productId,
                                 @PathVariable("qty") Integer quantity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        double subtotal = cartServices.updateQuantity(productId, quantity, (User) principal);

        return String.valueOf(subtotal);
    }

    @GetMapping("/cart/delete/{pid}")
    public String deleteProductFromCart(@PathVariable("pid") Long productId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartServices.deleteProduct(productId, (User) principal);

        return "Товар был удален из вашей корзины";
    }


}
