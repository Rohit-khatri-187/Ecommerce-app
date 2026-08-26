package com.rohit.ecommerce_backend.repositories;

import com.rohit.ecommerce_backend.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("""
        SELECT c
        FROM CartItem c
        WHERE c.user.id = :userId
    """)
    List<CartItem> findCartItemsByUserId(@Param("userId") Long userId);


    @Query("""
        SELECT c
        FROM CartItem c
        WHERE c.user.id = :userId
        AND c.product.id = :productId
    """)
    CartItem findCartItemByUserIdAndProductId(
            @Param("userId") Long userId,
            @Param("productId") Long productId
    );



}
