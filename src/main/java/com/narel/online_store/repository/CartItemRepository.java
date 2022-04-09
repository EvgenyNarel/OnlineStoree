package com.narel.online_store.repository;

import com.narel.online_store.dao.User;
import com.narel.online_store.model.CartItem;
import com.narel.online_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser(User user);

    CartItem findByUserAndProduct(User user, Product product);


    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id=?2 " + "AND c.user.id=?3")
    @Modifying
    void updateQuantity(Integer quantity, Long productId, Long userId);

    @Query("DELETE FROM CartItem c WHERE c.user.id=?1 AND c.product.id=?2")
    @Modifying
    void deleteByUserAndProduct(Long userId, Long productId);

    @Query("UPDATE CartItem c SET c.formalized = 1 WHERE c.user.id=?1")
    @Modifying
    void updateFormalized(Long userId);

}
