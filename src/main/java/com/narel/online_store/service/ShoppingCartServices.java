package com.narel.online_store.service;

import com.narel.online_store.dao.User;
import com.narel.online_store.model.CartItem;
import com.narel.online_store.model.Product;
import com.narel.online_store.repository.CartItemRepository;
import com.narel.online_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServices {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> listCartItems(User user) {
        return cartRepo.findByUser(user);
    }

    public Integer add(Long productId, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        CartItem cartItem = cartRepo.findByUserAndProduct(user, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }
        cartRepo.save(cartItem);

        return addedQuantity;
    }

    public double updateQuantity(Long productId, Integer quantity, User user) {
        cartRepo.updateQuantity(quantity, productId, user.getId());
        Product product = productRepository.findById(productId).get();

        double subtotal = product.getPrice() * quantity;
        return subtotal;
    }

    public void deleteProduct(Long productId, User user) {
        cartRepo.deleteByUserAndProduct(user.getId(), productId);
    }

    public void updateFormalized(User user) {
        cartRepo.updateFormalized(user.getId());
    }


}
